#APP_PLATFORM=android-8
APP_PLATFORM := android-9
APP_CFLAGS += -Wno-error=format-security

# The ARMv7 is significanly faster due to the use of the hardware FPU
APP_ABI := all
#APP_PLATFORM := android-9
APP_STL := gnustl_static