#include <jni.h>
#include "bspatch.h"

/* Header for class com_bd_update_Patch */

#ifndef _Included_com_bd_update_Patch
#define _Included_com_bd_update_Patch
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_bd_update_Patch
 * Method:    patcher
 * Signature: ()V
 */
JNIEXPORT jint JNICALL Java_com_bd_update_Patch_patcher
  (JNIEnv *, jobject, jstring argv1, jstring argv2, jstring argv3);

#ifdef __cplusplus
}
#endif
#endif
