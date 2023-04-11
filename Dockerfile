# This Dockerfile creates a static build image for CI

FROM openjdk:11-jdk

# Just matched `app/build.gradle`
ENV ANDROID_COMPILE_SDK "33"
# Just matched `app/build.gradle`
ENV ANDROID_BUILD_TOOLS "30.0.6"
# Version from https://developer.android.com/studio/releases/sdk-tools
ENV ANDROID_SDK_TOOLS "24.4.1"
ENV CMD_TOOLS "6858069"

ENV ANDROID_SDK_ROOT /android-sdk-linux

# install OS packages
RUN apt-get --quiet update --yes
RUN apt-get --quiet install --yes wget apt-utils tar unzip lib32stdc++6 lib32z1 build-essential ruby ruby-dev
# We use this for xxd hex->binary
RUN apt-get --quiet install --yes vim-common
# install Android SDK
RUN wget --quiet --output-document=android-sdk.tgz https://dl.google.com/android/android-sdk_r${ANDROID_SDK_TOOLS}-linux.tgz
RUN wget --quiet --output-document=android-sdk.zip https://dl.google.com/android/repository/commandlinetools-linux-${CMD_TOOLS}_latest.zip
RUN tar --extract --gzip --file=android-sdk.tgz
RUN mkdir -p $ANDROID_SDK_ROOT/cmdline-tools/latest/
RUN unzip -d $ANDROID_SDK_ROOT/cmdline-tools/latest/ android-sdk.zip
RUN echo y | android-sdk-linux/tools/android --silent update sdk --no-ui --all --filter android-${ANDROID_COMPILE_SDK}
RUN echo y | android-sdk-linux/tools/android --silent update sdk --no-ui --all --filter platform-tools
RUN echo y | android-sdk-linux/tools/android --silent update sdk --no-ui --all --filter build-tools-${ANDROID_BUILD_TOOLS}
RUN echo y | android-sdk-linux/tools/android --silent update sdk --no-ui --all --filter extra-android-m2repository
RUN echo y | android-sdk-linux/tools/android --silent update sdk --no-ui --all --filter extra-google-google_play_services
RUN echo y | android-sdk-linux/tools/android --silent update sdk --no-ui --all --filter extra-google-m2repository

ENV PATH=$PATH:$ANDROID_SDK_ROOT/cmdline-tools/latest/cmdline-tools/bin
ENV PATH "${PATH}:${ANDROID_SDK_ROOT}/tools:${ANDROID_SDK_ROOT}/tools/bin:${ANDROID_SDK_ROOT}/platform-tools"
RUN echo yes | sdkmanager --sdk_root="$ANDROID_SDK_ROOT" "platforms;android-${ANDROID_COMPILE_SDK}"

RUN 
# install FastLane
COPY Gemfile.lock .
COPY Gemfile .
RUN gem install bundler -v 1.16.6
RUN bundle install
