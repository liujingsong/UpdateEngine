LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
#bzlib模块
bzlib_files := \
	blocksort.c \
	huffman.c \
	crctable.c \
	randtable.c \
	compress.c \
	decompress.c \
	bzlib.c

LOCAL_MODULE := libbz
LOCAL_SRC_FILES := $(bzlib_files)
include $(BUILD_STATIC_LIBRARY)

#bspath模块
include $(CLEAR_VARS)
LOCAL_MODULE    := bspatch
LOCAL_SRC_FILES := bspatch.c
LOCAL_C_INCLUDES := bspatch.h
LOCAL_STATIC_LIBRARIES := libbz #引入libbz库

include $(BUILD_STATIC_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE    := Patcher
LOCAL_SRC_FILES := Patcher.c
LOCAL_STATIC_LIBRARIES := bspatch
LOCAL_LDLIBS :=  -llog#加入log

include $(BUILD_SHARED_LIBRARY)

#andfix
include $(CLEAR_VARS)
LOCAL_SRC_FILES:= andfix.cpp\
					art/art_method_replace.cpp \
					art/art_method_replace_5_0.cpp \
					art/art_method_replace_5_1.cpp \
					art/art_method_replace_6_0.cpp \
					dalvik/dalvik_method_replace.cpp \

LOCAL_CFLAGS	:= -std=gnu++11 -fpermissive -DDEBUG -O0

LOCAL_C_INCLUDES :=

LOCAL_SHARED_LIBRARIES :=

LOCAL_LDLIBS    := -llog

LOCAL_STATIC_LIBRARIES :=

LOCAL_MODULE:= andfix

include $(BUILD_SHARED_LIBRARY)
