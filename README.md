# What

一个以`上传软件包到蒲公英平台`为核心功能的 IDEA 插件

目前具有的功能

1. 选择本地软件包后, 直接上传到蒲公英进行版本的发布

   无需 `打开浏览器->打开蒲公英网页->打开上传页面 -> 选择文件 -> 进行上传` 的繁琐步骤

2. 一键打包并上传软件包

   配置好命令和环境后, 打包和上传流程可以一键完成

# How



# Project

## 开发环境

1. PC

   ```cpp
   windows 11
   ```

2. IDE

   ```cpp
   IntelliJ IDEA 2025.2.4 (Ultimate Edition)
     
   Build #IU-252.27397.103, built on October 23, 2025
   
   Licensed to Doreen Clarissa
   Subscription is active until September 7, 2026.
   For educational use only.
   
   Runtime version: 21.0.8+1-b1038.73 amd64 (JCEF 122.1.9)
   VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
   
   Kotlin plugin: K1 mode
   ```



## 打包

1. 命令

   ```cpp
   ./gradlew buildPlugin
   ```

2. 产物生成目录

   ```cpp
   .\PgyAppUpload\build\distributions
   ```

   
