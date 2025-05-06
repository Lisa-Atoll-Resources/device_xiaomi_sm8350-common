# Clone Resources
git clone https://gitlab.com/911-NSR/vendor-xiaomi-lisa.git -b lineage-22.2 vendor/xiaomi/lisa
git clone https://github.com/NSR-Resources/vendor_xiaomi_sm8350-common.git -b derp vendor/xiaomi/sm8350-common
git clone https://github.com/NSR-Resources/kernel_xiaomi_lisa.git kernel/xiaomi/lisa

# Clone Hardware Xiaomi
rm -rf hardware/xiaomi
git clone https://github.com/NSR-Resources/hardware_xiaomi.git -b lineage-22.2 hardware/xiaomi

# Clone Miui Camera
git clone https://gitlab.com/911-NSR/vendor-xiaomi-miuicamera-lisa.git -b lineage-22.2 vendor/xiaomi/miuicamera-lisa

# Include BCR
git clone https://github.com/911-NSR/android_vendor_bcr.git vendor/bcr
