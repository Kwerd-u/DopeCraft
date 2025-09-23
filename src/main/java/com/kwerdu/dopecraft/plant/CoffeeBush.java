package com.kwerdu.dopecraft.plant;

import com.kwerdu.dopecraft.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class CoffeeBush extends BushBlock implements BonemealableBlock {
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 4);
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(3.0, 0.0, 3.0, 13.0, 8.0, 13.0),  // Возраст 0
            Block.box(2.0, 0.0, 2.0, 14.0, 10.0, 14.0), // Возраст 1
            Block.box(1.0, 0.0, 1.0, 15.0, 12.0, 15.0), // Возраст 2
            Block.box(1.0, 0.0, 1.0, 15.0, 14.0, 15.0), // Возраст 3
            Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0)   // Возраст 4 (зрелый)
    };

    public CoffeeBush(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[state.getValue(AGE)];
    }

    @Override
    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        if (state.getValue(AGE) >= 2 && entity instanceof LivingEntity) {
            entity.makeStuckInBlock(state, new Vec3(0.8F, 0.75D, 0.8F));
        }
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos) {
        return state.is(Blocks.GRASS_BLOCK) ||
                state.is(Blocks.DIRT) ||
                state.is(Blocks.PODZOL) ||
                state.is(Blocks.COARSE_DIRT) ||
                state.is(Blocks.FARMLAND);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        int age = state.getValue(AGE);
        if (age < 4 && world.getRawBrightness(pos.above(), 0) >= 9 && random.nextInt(5) == 0) {
            world.setBlock(pos, state.setValue(AGE, age + 1), 2);
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean b) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return state.getValue(AGE) < 4;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        int newAge = Math.min(state.getValue(AGE) + 1, 4);
        world.setBlock(pos, state.setValue(AGE, newAge), 2);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        int i = (Integer)pState.getValue(AGE);
        boolean flag = i == 4;
        if (!flag && pPlayer.getItemInHand(pHand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else if (flag) {
            int j = 1 + pLevel.random.nextInt(2);
            popResource(pLevel, pPos, new ItemStack(ModItems.COFFEE_BEANS.get(), j + (flag ? 1 : 0)));
            pLevel.playSound((Player)null, pPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + pLevel.random.nextFloat() * 0.4F);
            BlockState blockstate = (BlockState)pState.setValue(AGE, 2);
            pLevel.setBlock(pPos, blockstate, 2);
            pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(pPlayer, blockstate));
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else {
            return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
        }
    }



    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
        return new ItemStack(ModItems.COFFEE_BEANS.get());
    }
}
