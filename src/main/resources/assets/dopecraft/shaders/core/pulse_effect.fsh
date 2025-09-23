#version 150
in vec2 texCoord;
out vec4 fragColor;

uniform sampler2D DiffuseSampler;
uniform float Time;
uniform float Intensity;

void main() {
    vec4 col = texture(DiffuseSampler, texCoord);
    float wave = 0.5 + 0.5 * sin(Time * 2.0);
    // Простейшее пульсирующее затемнение
    fragColor = vec4(col.rgb * mix(1.0, 0.75, wave) * Intensity, col.a);
}
