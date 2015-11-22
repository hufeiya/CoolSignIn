package com.hufeiya.SignIn.contrib;

/**
 * Created by hufeiya on 15-11-15.
 */
/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/**
 * Represents a resource of an application under test which can cause asynchronous background work
 * to happen during test execution (e.g. an intent service that processes a button click). By
 * default,synchronizes all view operations with the UI thread as well as
 * AsyncTasks; however, it has no way of doing so with "hand-made" resources. In such cases, test
 * authors can register the custom resource and will wait for the resource to
 * become idle prior to executing a view operation.
 * <br><br>
 * <b>Important Note:</b> it is assumed that the resource stays idle most of the time.
 */
public interface IdlingResource {

}
