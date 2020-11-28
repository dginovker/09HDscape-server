package org.runite.client;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;

import java.nio.ByteBuffer;


class HDSprite extends AbstractSprite {

    int anInt4074 = 0;
    int anInt4075;
    int anInt4077 = -1;
    int anInt4079;
    private int anInt4076 = -1;
    private int anInt4078 = 0;
    private int anInt4080;


    HDSprite(int var1, int var2, int var3, int var4, int var5, int var6, int[] var7) {
        this.anInt3697 = var1;
        this.anInt3706 = var2;
        this.anInt3701 = var3;
        this.anInt3698 = var4;
        this.anInt3707 = var5;
        this.anInt3696 = var6;
        this.method650(var7);
        this.method651();
    }

    HDSprite(Class3_Sub28_Sub16_Sub2 var1) {
        this.anInt3697 = var1.anInt3697;
        this.anInt3706 = var1.anInt3706;
        this.anInt3701 = var1.anInt3701;
        this.anInt3698 = var1.anInt3698;
        this.anInt3707 = var1.anInt3707;
        this.anInt3696 = var1.anInt3696;
        this.method650(var1.anIntArray4081);
        this.method651();
    }

    private void method644(int var1) {
        if (this.anInt4078 != var1) {
            this.anInt4078 = var1;
            if (var1 == 2) {
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
            } else {
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            }

        }
    }

    final void drawMinimapIcons(int interfaceWidth, int interfaceHeight, HDSprite var3) {
        if (var3 != null) {
            HDToolKit.method1822();
            HDToolKit.bindTexture2D(var3.anInt4077);
            var3.method644(1);
            HDToolKit.bindTexture2D(this.anInt4077);
            this.method644(1);
            glActiveTexture(GL_TEXTURE1);
            glEnable(GL_TEXTURE_2D);
            glBindTexture(GL_TEXTURE_2D, var3.anInt4077);
            glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_REPLACE);
            glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_RGB, GL_PREVIOUS);
            float var5 = (float) (interfaceWidth - Class22.anInt449) / (float) var3.anInt4075;
            float var6 = (float) (interfaceHeight - Class22.anInt448) / (float) var3.anInt4079;
            float var7 = (float) (interfaceWidth + this.anInt3707 - Class22.anInt449) / (float) var3.anInt4075;
            float var8 = (float) (interfaceHeight + this.anInt3696 - Class22.anInt448) / (float) var3.anInt4079;
            interfaceWidth += this.anInt3701;
            interfaceHeight += this.anInt3698;
            glBegin(GL_TRIANGLE_FAN);
            glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            float var9 = (float) this.anInt3707 / (float) this.anInt4075;
            float var10 = (float) this.anInt3696 / (float) this.anInt4079;
            glMultiTexCoord2f(GL_TEXTURE1, var7, var6);
            glTexCoord2f(var9, 0.0F);
            glVertex2f((float) (interfaceWidth + this.anInt3707), (float) (HDToolKit.viewHeight - interfaceHeight));
            glMultiTexCoord2f(GL_TEXTURE1, var5, var6);
            glTexCoord2f(0.0F, 0.0F);
            glVertex2f((float) interfaceWidth, (float) (HDToolKit.viewHeight - interfaceHeight));
            glMultiTexCoord2f(GL_TEXTURE1, var5, var8);
            glTexCoord2f(0.0F, var10);
            glVertex2f((float) interfaceWidth, (float) (HDToolKit.viewHeight - (interfaceHeight + this.anInt3696)));
            glMultiTexCoord2f(GL_TEXTURE1, var7, var8);
            glTexCoord2f(var9, var10);
            glVertex2f((float) (interfaceWidth + this.anInt3707), (float) (HDToolKit.viewHeight - (interfaceHeight + this.anInt3696)));
            glEnd();
            glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_MODULATE);
            glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_RGB, GL_TEXTURE);
            glDisable(GL_TEXTURE_2D);
            glActiveTexture(GL_TEXTURE0);
        }
    }

    final void method646(int var1, int var2, int var3, int var4, int var5) {
        HDToolKit.method1828();
        HDToolKit.bindTexture2D(this.anInt4077);
        this.method644(1);
        float var7 = (float) this.anInt3707 / (float) this.anInt4075;
        float var8 = (float) this.anInt3696 / (float) this.anInt4079;
        var7 *= (float) var4;
        var8 *= (float) var5;
        int var9 = var1 + this.anInt3701;
        int var10 = var9 + this.anInt3707 * var4;
        int var11 = HDToolKit.viewHeight - var2 - this.anInt3698;
        int var12 = var11 - this.anInt3696 * var5;
        float var13 = (float) var3 / 256.0F;
        glBegin(GL_TRIANGLE_FAN);
        glColor4f(1.0F, 1.0F, 1.0F, var13);
        glTexCoord2f(var7, 0.0F);
        glVertex2f((float) var10, (float) var11);
        glTexCoord2f(0.0F, 0.0F);
        glVertex2f((float) var9, (float) var11);
        glTexCoord2f(0.0F, var8);
        glVertex2f((float) var9, (float) var12);
        glTexCoord2f(var7, var8);
        glVertex2f((float) var10, (float) var12);
        glEnd();
    }

    final void drawMinimapRegion(int x, int y, int width, int height, int playerRelativeX, int playerRelativeY, int regionRotation, int zoom, HDSprite var9) {
        if (var9 != null) {
            HDToolKit.method1822();
            HDToolKit.bindTexture2D(var9.anInt4077);
            var9.method644(1);
            HDToolKit.bindTexture2D(this.anInt4077);
            this.method644(1);
            glActiveTexture(GL_TEXTURE1);
            glEnable(GL_TEXTURE_2D);
            glBindTexture(GL_TEXTURE_2D, var9.anInt4077);
            glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_REPLACE);
            glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_RGB, GL_PREVIOUS);
            int var11 = -width / 2;
            int var12 = -height / 2;
            int var13 = -var11;
            int var14 = -var12;
            int var15 = (int) (Math.sin((double) regionRotation / 326.11D) * 65536.0D);
            int var16 = (int) (Math.cos((double) regionRotation / 326.11D) * 65536.0D);
            var15 = var15 * zoom >> 8;
            var16 = var16 * zoom >> 8;
            int var17 = (playerRelativeX << 16) + var12 * var15 + var11 * var16;
            int var18 = (playerRelativeY << 16) + (var12 * var16 - var11 * var15);
            int var19 = (playerRelativeX << 16) + var12 * var15 + var13 * var16;
            int var20 = (playerRelativeY << 16) + (var12 * var16 - var13 * var15);
            int var21 = (playerRelativeX << 16) + var14 * var15 + var11 * var16;
            int var22 = (playerRelativeY << 16) + (var14 * var16 - var11 * var15);
            int var23 = (playerRelativeX << 16) + var14 * var15 + var13 * var16;
            int var24 = (playerRelativeY << 16) + (var14 * var16 - var13 * var15);
            float var25 = (float) var9.anInt3707 / (float) var9.anInt4075;
            float var26 = (float) var9.anInt3696 / (float) var9.anInt4079;
            glBegin(GL_TRIANGLE_FAN);
            glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            float var27 = 65536.0F * (float) this.anInt4075;
            float var28 = (float) (65536 * this.anInt4079);
            glMultiTexCoord2f(GL_TEXTURE1, var25, 0.0F);
            glTexCoord2f((float) var19 / var27, (float) var20 / var28);
            glVertex2f((float) (x + width), (float) (HDToolKit.viewHeight - y));
            glMultiTexCoord2f(GL_TEXTURE1, 0.0F, 0.0F);
            glTexCoord2f((float) var17 / var27, (float) var18 / var28);
            glVertex2f((float) x, (float) (HDToolKit.viewHeight - y));
            glMultiTexCoord2f(GL_TEXTURE1, 0.0F, var26);
            glTexCoord2f((float) var21 / var27, (float) var22 / var28);
            glVertex2f((float) x, (float) (HDToolKit.viewHeight - (y + height)));
            glMultiTexCoord2f(GL_TEXTURE1, var25, var26);
            glTexCoord2f((float) var23 / var27, (float) var24 / var28);
            glVertex2f((float) (x + width), (float) (HDToolKit.viewHeight - (y + height)));
            glEnd();
            glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_MODULATE);
            glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_RGB, GL_TEXTURE);
            glDisable(GL_TEXTURE_2D);
            glActiveTexture(GL_TEXTURE0);
        }
    }

    final void method641(int var1, int var2) {
        HDToolKit.method1822();
        var1 += this.anInt3701;
        var2 += this.anInt3698;
        HDToolKit.bindTexture2D(this.anInt4077);
        this.method644(1);
        glTranslatef((float) var1, (float) (HDToolKit.viewHeight - var2), 0.0F);
        float var4 = (float) this.anInt3707 / (float) this.anInt4075;
        float var5 = (float) this.anInt3696 / (float) this.anInt4079;
        glBegin(GL_TRIANGLE_FAN);
        glTexCoord2f(0.0F, 0.0F);
        glVertex2f((float) this.anInt3707, 0.0F);
        glTexCoord2f(var4, 0.0F);
        glVertex2f(0.0F, 0.0F);
        glTexCoord2f(var4, var5);
        glVertex2f(0.0F, (float) (-this.anInt3696));
        glTexCoord2f(0.0F, var5);
        glVertex2f((float) this.anInt3707, (float) (-this.anInt3696));
        glEnd();
        glLoadIdentity();
    }

    final void method643(int var1, int var2) {
        HDToolKit.method1822();
        var1 += this.anInt3701;
        var2 += this.anInt3698;
        HDToolKit.bindTexture2D(this.anInt4077);
        this.method644(1);
        glTranslatef((float) var1, (float) (HDToolKit.viewHeight - var2), 0.0F);
        glCallList(this.anInt4076);
        glLoadIdentity();
    }

    final void method648(int var1, int var2, int var3, int var4, int var5) {
        HDToolKit.method1822();
        HDToolKit.bindTexture2D(this.anInt4077);
        this.method644(2);
        var1 -= this.anInt3701 << 4;
        var2 -= this.anInt3698 << 4;
        glTranslatef((float) var3 / 16.0F, (float) HDToolKit.viewHeight - (float) var4 / 16.0F, 0.0F);
        glRotatef((float) (-var5) * 0.005493164F, 0.0F, 0.0F, 1.0F);
        glTranslatef((float) (-var1) / 16.0F, (float) var2 / 16.0F, 0.0F);
        glCallList(this.anInt4076);
        glLoadIdentity();
    }

    final void method639(int var1, int var2, int var3, int var4) {
        if (var3 > 0 && var4 > 0) {
            HDToolKit.method1822();
            int var5 = this.anInt3707;
            int var6 = this.anInt3696;
            int var7 = 0;
            int var8 = 0;
            int var9 = this.anInt3697;
            int var10 = this.anInt3706;
            int var11 = (var9 << 16) / var3;
            int var12 = (var10 << 16) / var4;
            int var13;
            if (this.anInt3701 > 0) {
                var13 = ((this.anInt3701 << 16) + var11 - 1) / var11;
                var1 += var13;
                var7 += var13 * var11 - (this.anInt3701 << 16);
            }

            if (this.anInt3698 > 0) {
                var13 = ((this.anInt3698 << 16) + var12 - 1) / var12;
                var2 += var13;
                var8 += var13 * var12 - (this.anInt3698 << 16);
            }

            if (var5 < var9) {
                var3 = ((var5 << 16) - var7 + var11 - 1) / var11;
            }

            if (var6 < var10) {
                var4 = ((var6 << 16) - var8 + var12 - 1) / var12;
            }

            HDToolKit.bindTexture2D(this.anInt4077);
            this.method644(2);
            float var14 = (float) var1;
            float var15 = var14 + (float) var3;
            float var16 = (float) (HDToolKit.viewHeight - var2);
            float var17 = var16 - (float) var4;
            float var18 = (float) this.anInt3707 / (float) this.anInt4075;
            float var19 = (float) this.anInt3696 / (float) this.anInt4079;
            glBegin(GL_TRIANGLE_FAN);
            glTexCoord2f(var18, 0.0F);
            glVertex2f(var15, var16);
            glTexCoord2f(0.0F, 0.0F);
            glVertex2f(var14, var16);
            glTexCoord2f(0.0F, var19);
            glVertex2f(var14, var17);
            glTexCoord2f(var18, var19);
            glVertex2f(var15, var17);
            glEnd();
        }
    }

    final void method635(int var1, int var2) {
        HDToolKit.method1822();
        var1 += this.anInt3701;
        var2 += this.anInt3698;
        HDToolKit.bindTexture2D(this.anInt4077);
        this.method644(1);
        glTranslatef((float) var1, (float) (HDToolKit.viewHeight - var2), 0.0F);
        glCallList(this.anInt4076);
        glLoadIdentity();
    }

    protected final void finalize() throws Throwable {
        if (this.anInt4077 != -1) {
            Class31.method991(this.anInt4077, this.anInt4074, this.anInt4080);
            this.anInt4077 = -1;
            this.anInt4074 = 0;
        }

        if (this.anInt4076 != -1) {
            Class31.method986(this.anInt4076, this.anInt4080);
            this.anInt4076 = -1;
        }

        super.finalize();
    }

    final void method636(int var1, int var2, int var3, int var4, int var5, int var6) {
        HDToolKit.method1822();
        HDToolKit.bindTexture2D(this.anInt4077);
        this.method644(1);
        var1 -= this.anInt3701 << 4;
        var2 -= this.anInt3698 << 4;
        glTranslatef((float) var3 / 16.0F, (float) HDToolKit.viewHeight - (float) var4 / 16.0F, 0.0F);
        glRotatef((float) var5 * 0.005493164F, 0.0F, 0.0F, 1.0F);
        if (var6 != 4096) {
            glScalef((float) var6 / 4096.0F, (float) var6 / 4096.0F, 0.0F);
        }

        glTranslatef((float) (-var1) / 16.0F, (float) var2 / 16.0F, 0.0F);
        glCallList(this.anInt4076);
        glLoadIdentity();
    }

    final void method642(int var1, int var2, int var3, int var4, int var5) {
        if (var3 > 0 && var4 > 0) {
            HDToolKit.method1828();
            int var6 = this.anInt3707;
            int var7 = this.anInt3696;
            int var8 = 0;
            int var9 = 0;
            int var10 = this.anInt3697;
            int var11 = this.anInt3706;
            int var12 = (var10 << 16) / var3;
            int var13 = (var11 << 16) / var4;
            int var14;
            if (this.anInt3701 > 0) {
                var14 = ((this.anInt3701 << 16) + var12 - 1) / var12;
                var1 += var14;
                var8 += var14 * var12 - (this.anInt3701 << 16);
            }

            if (this.anInt3698 > 0) {
                var14 = ((this.anInt3698 << 16) + var13 - 1) / var13;
                var2 += var14;
                var9 += var14 * var13 - (this.anInt3698 << 16);
            }

            if (var6 < var10) {
                var3 = ((var6 << 16) - var8 + var12 - 1) / var12;
            }

            if (var7 < var11) {
                var4 = ((var7 << 16) - var9 + var13 - 1) / var13;
            }

            HDToolKit.bindTexture2D(this.anInt4077);
            this.method644(1);
            float var15 = (float) var1;
            float var16 = var15 + (float) var3;
            float var17 = (float) (HDToolKit.viewHeight - var2);
            float var18 = var17 - (float) var4;
            float var19 = (float) this.anInt3707 / (float) this.anInt4075;
            float var20 = (float) this.anInt3696 / (float) this.anInt4079;
            float var21 = (float) var5 / 256.0F;
            glBegin(GL_TRIANGLE_FAN);
            glColor4f(1.0F, 1.0F, 1.0F, var21);
            glTexCoord2f(var19, 0.0F);
            glVertex2f(var16, var17);
            glTexCoord2f(0.0F, 0.0F);
            glVertex2f(var15, var17);
            glTexCoord2f(0.0F, var20);
            glVertex2f(var15, var18);
            glTexCoord2f(var19, var20);
            glVertex2f(var16, var18);
            glEnd();
        }
    }

    final void method649(int var1, int var2, int var3, int var4) {
        HDToolKit.method1822();
        HDToolKit.bindTexture2D(this.anInt4077);
        this.method644(1);
        float var6 = (float) this.anInt3707 / (float) this.anInt4075;
        float var7 = (float) this.anInt3696 / (float) this.anInt4079;
        var6 *= (float) var3;
        var7 *= (float) var4;
        int var8 = var1 + this.anInt3701;
        int var9 = var8 + this.anInt3707 * var3;
        int var10 = HDToolKit.viewHeight - var2 - this.anInt3698;
        int var11 = var10 - this.anInt3696 * var4;
        glBegin(GL_TRIANGLE_FAN);
        glTexCoord2f(var6, 0.0F);
        glVertex2f((float) var9, (float) var10);
        glTexCoord2f(0.0F, 0.0F);
        glVertex2f((float) var8, (float) var10);
        glTexCoord2f(0.0F, var7);
        glVertex2f((float) var8, (float) var11);
        glTexCoord2f(var6, var7);
        glVertex2f((float) var9, (float) var11);
        glEnd();
    }

    void method650(int[] var1) {
        this.anInt4075 = Class95.method1585((byte) 125, this.anInt3707);
        this.anInt4079 = Class95.method1585((byte) 59, this.anInt3696);
        byte[] var2 = new byte[this.anInt4075 * this.anInt4079 * 4];
        int var3 = 0;
        int var4 = 0;
        int var5 = (this.anInt4075 - this.anInt3707) * 4;

        for (int var6 = 0; var6 < this.anInt3696; ++var6) {
            for (int var7 = 0; var7 < this.anInt3707; ++var7) {
                int var8 = var1[var4++];
                if (var8 == 0) {
                    var3 += 4;
                } else {
                    var2[var3++] = (byte) (var8 >> 16);
                    var2[var3++] = (byte) (var8 >> 8);
                    var2[var3++] = (byte) var8;
                    var2[var3++] = -1;
                }
            }

            var3 += var5;
        }

        ByteBuffer var9 = ByteBuffer.wrap(var2);
        if (this.anInt4077 == -1) {
            int[] var11 = new int[1];
            glGenTextures(var11);//1, array, 0
            this.anInt4077 = var11[0];
            this.anInt4080 = Class31.anInt582;
        }

        HDToolKit.bindTexture2D(this.anInt4077);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, this.anInt4075, this.anInt4079, 0, GL_RGBA, GL_UNSIGNED_BYTE, var9);
        Class31.memory2D += var9.limit() - this.anInt4074;
        this.anInt4074 = var9.limit();
    }

    final void method637(int var1, int var2, int var3) {
        HDToolKit.method1828();
        var1 += this.anInt3701;
        var2 += this.anInt3698;
        HDToolKit.bindTexture2D(this.anInt4077);
        this.method644(1);
        glColor4f(1.0F, 1.0F, 1.0F, (float) var3 / 256.0F);
        glTranslatef((float) var1, (float) (HDToolKit.viewHeight - var2), 0.0F);
        glCallList(this.anInt4076);
        glLoadIdentity();
    }

    private void method651() {
        float var1 = (float) this.anInt3707 / (float) this.anInt4075;
        float var2 = (float) this.anInt3696 / (float) this.anInt4079;
        if (this.anInt4076 == -1) {
            this.anInt4076 = glGenLists(1);
            this.anInt4080 = Class31.anInt582;
        }

        glNewList(this.anInt4076, GL_COMPILE);
        glBegin(GL_TRIANGLE_FAN);
        glTexCoord2f(var1, 0.0F);
        glVertex2f((float) this.anInt3707, 0.0F);
        glTexCoord2f(0.0F, 0.0F);
        glVertex2f(0.0F, 0.0F);
        glTexCoord2f(0.0F, var2);
        glVertex2f(0.0F, (float) (-this.anInt3696));
        glTexCoord2f(var1, var2);
        glVertex2f((float) this.anInt3707, (float) (-this.anInt3696));
        glEnd();
        glEndList();
    }
}
