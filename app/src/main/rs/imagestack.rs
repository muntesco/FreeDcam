#pragma version(1)
    #pragma rs java_package_name(com.imageconverter)
    #pragma rs_fp_relaxed
    rs_allocation gCurrentFrame;
    rs_allocation gLastFrame;
    uchar4 __attribute__((kernel)) stackimage_avarage(uint32_t x, uint32_t y) {
        uchar4 curPixel, lastPixel;
        curPixel = rsGetElementAt_uchar4(gCurrentFrame, x, y);
        lastPixel = rsGetElementAt_uchar4(gLastFrame, x, y);
        int4 rgb;
        //rsDebug("curPixel", curPixel);
        //rsDebug("lastPixel", lastPixel);
        rgb.r = curPixel.r/2 + lastPixel.r/2;
        rgb.g = curPixel.g/2 + lastPixel.b/2;
        rgb.b = curPixel.b/2 + lastPixel.b/2;
        rgb.a = 255;
        if (rgb.r > 255) rgb.r = 255; if(rgb.r < 0) rgb.r = 0;
        if (rgb.g > 255) rgb.g = 255; if(rgb.g < 0) rgb.g = 0;
        if (rgb.b > 255) rgb.b = 255; if(rgb.b < 0) rgb.b = 0;

        //rsDebug("rgb", rgb);
        uchar4 out = convert_uchar4(rgb);
        return out;
    }

    uchar4 __attribute__((kernel)) stackimage_lighten(uint32_t x, uint32_t y) {
    uchar4 curPixel, lastPixel;
    curPixel = rsGetElementAt_uchar4(gCurrentFrame, x, y);
    lastPixel = rsGetElementAt_uchar4(gLastFrame, x, y);
    int4 rgb;

    if(curPixel > lastPixel)
        rgb = curPixel;
    else
        rgb = lastPixel;
    rgb.a = 255;
    if (rgb.r > 255) rgb.r = 255; if(rgb.r < 0) rgb.r = 0;
    if (rgb.g > 255) rgb.g = 255; if(rgb.g < 0) rgb.g = 0;
    if (rgb.b > 255) rgb.b = 255; if(rgb.b < 0) rgb.b = 0;
    uchar4 out = convert_uchar4(rgb);
    return out;
    }