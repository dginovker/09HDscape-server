package org.runite.client;

import org.rs09.client.config.GameConfig;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

final class Class92 {

    private static final float[] light1Position = new float[4];
    static float[] light0Position = new float[4];
    static int lightX;
    static int lightY;
    static int defaultRegionAmbientRGB = 13156520;//13156520
    static float[] fogColor = new float[4];
    static int defaultScreenColorRgb = 16777215;//16777215
    private static int screenColorRgb = -1;
    private static float light0Diffuse = -1.0F;
    private static float light1Diffuse = -1.0F;
    private static float lightModelAmbient;
    private static int fogOffset = -1;
    private static int fogColorRGB = -1;


    static void method1504() {
        glLightfv(GL_LIGHT0,GL_POSITION, light0Position);
        glLightfv(GL_LIGHT1, GL_POSITION, light1Position);
    }

    static float getLightingModelAmbient() {
        return light0Diffuse;
    }

    static void setLightParams(int color, float ambientMod, float l0Diffuse, float l1Diffuse) {
        if (screenColorRgb != color || lightModelAmbient != ambientMod || light0Diffuse != l0Diffuse || light1Diffuse != l1Diffuse) {
            screenColorRgb = color;
            lightModelAmbient = ambientMod;
            light0Diffuse = l0Diffuse;
            light1Diffuse = l1Diffuse;
            final float red = (color >> 16 & 0xff) / 255.0F;
            final float green = (color >> 8 & 0xff) / 255.0F;
            final float blue = (color & 0xff) / 255.0F;
            final float[] lightModelAmbientParams = {ambientMod * red, ambientMod * green, ambientMod * blue, 1.0F};
            glLightModelfv(GL_LIGHT_MODEL_AMBIENT, lightModelAmbientParams);//LIGHT_MODEL_AMBIENT
            final float[] light0Params = {l0Diffuse * red, l0Diffuse * green, l0Diffuse * blue, 1.0F};
            glLightfv(GL_LIGHT0,GL_DIFFUSE, light0Params);//LIGHT0, DIFFUSE
            final float[] light1Params = {-l1Diffuse * red, -l1Diffuse * green, -l1Diffuse * blue, 1.0F};
            glLightfv(GL_LIGHT1, GL_DIFFUSE, light1Params);//LIGHT1, DIFFUSE
        }
    }

    static void setFogValues(int fogCol, int fogOff) {
        if (fogColorRGB != fogCol || fogOffset != fogOff) {
            fogColorRGB = fogCol;
            fogOffset = fogOff;
            byte lowestFogStart = 50;
            //short baseFogStart = 3584; This is unused because it was originally this but to avoid math jagex simplified it.
            fogColor[0] = (fogCol >> 16 & 0xff) / 255.0F;
            fogColor[1] = (fogCol >> 8 & 0xff) / 255.0F;
            fogColor[2] = (fogCol & 0xff) / 255.0F;
            //2917 FOG_MODE
            //9729 LINEAR
            glFogi(GL_FOG_MODE, GL_LINEAR);
            //FOG_DENSITY
            glFogf(GL_FOG_DENSITY, 0.95F);
            //3156 = FOG_HINT
            //4353 = FASTEST, 4354 = NICEST, 4352 = DONT_CARE
            glHint(GL_FOG_HINT, GL_FASTEST);
            int fogStart = 3072 - fogOff;//baseFogStart - 512 - fogOff
            if (fogStart < lowestFogStart) {
                fogStart = lowestFogStart;
            }
            //FOG_START
            glFogf(GL_FOG_START, GameConfig.RENDER_DISTANCE_FOG_FIX - 256);//3072
            //FOG_END
            glFogf(GL_FOG_END, GameConfig.RENDER_DISTANCE_FOG_FIX);//baseFogStart - 256
            //FOG_COLOR
            glFogfv(GL_FOG_COLOR, fogColor);
        }
    }

    static void setLightPosition(float x, float y, float z) {
        if (light0Position[0] != x || light0Position[1] != y || light0Position[2] != z) {
            light0Position[0] = x;
            light0Position[1] = y;
            light0Position[2] = z;
            light1Position[0] = -x;
            light1Position[1] = -y;
            light1Position[2] = -z;
            lightX = (int) (x * 256.0F / y);
            lightY = (int) (z * 256.0F / y);
        }
    }

    static int screenColorRgb() {
        return screenColorRgb;
    }

    static void method1511() {
        glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);//FRONT, AMBIENT_AND_DIFFUSE
        glEnable(GL_COLOR_MATERIAL);//COLOR_MATERIAL
        final float[] light0Params = {0.0F, 0.0F, 0.0F, 1.0F};
        glLightfv(GL_LIGHT0,GL_AMBIENT, light0Params);//LIGHT0, AMBIENT
        glEnable(GL_LIGHT0);//LIGHT0
        final float[] light1Params = {0.0F, 0.0F, 0.0F, 1.0F};
        glLightfv(GL_LIGHT1, GL_AMBIENT, light1Params);//LIGHT1, AMBIENT
        glEnable(GL_LIGHT1);//LIGHT1
        screenColorRgb = -1;
        fogColorRGB = -1;
        initDefaults();
    }

    static void method1512(float[] var0) {
        if (var0 == null) {
            var0 = fogColor;
        }
        glFogfv(GL_FOG_COLOR, var0);
    }

    private static void initDefaults() {
        setLightParams(defaultScreenColorRgb, 1.1523438F, 0.69921875F, 1.2F);
        setLightPosition(-50.0F, -60.0F, -50.0F);
        setFogValues(defaultRegionAmbientRGB, 0);
    }

    static float method1514() {
        return lightModelAmbient;
    }

}
