#
# Copyright (C) 2022 The LineageOS Project
#
# SPDX-License-Identifier: Apache-2.0
#

# Inherit from lisa device
$(call inherit-product, device/xiaomi/lisa/device.mk)

# Inherit some common Lineage stuff.
$(call inherit-product, vendor/lineage/config/common_full_phone.mk)

# Bootanimation
TARGET_BOOTANIMATION_RES=1080

# Remove unwanted apps
TARGET_INCLUDE_ACCORD := false
PRODUCT_NO_CAMERA := false

PRODUCT_BRAND := Xiaomi
PRODUCT_DEVICE := lisa
PRODUCT_MANUFACTURER := Xiaomi
PRODUCT_MODEL := 2109119DG
PRODUCT_NAME := lineage_lisa

PRODUCT_GMS_CLIENTID_BASE := android-xiaomi

PRODUCT_BUILD_PROP_OVERRIDES += \
    BuildDesc="lisa_global-user 14 UKQ1.231108.001 V816.0.10.0.UKOMIXM release-keys" \
    BuildFingerprint=Xiaomi/lisa_global/lisa:14/UKQ1.231108.001/V816.0.10.0.UKOMIXM:user/release-keys
