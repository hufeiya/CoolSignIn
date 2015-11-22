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
package com.hufeiya.SignIn.contrib;

import android.text.TextUtils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * An implementation of {@link IdlingResource} that determines idleness by maintaining an internal
 * counter. When the counter is 0 - it is considered to be idle, when it is non-zero it is not
 * idle. This is very similar to the way a {@link java.util.concurrent.Semaphore} behaves.
 * <p>
 * The counter may be incremented or decremented from any thread. If it reaches an illogical state
 * (like counter less than zero) it will throw an IllegalStateException.
 * </p>
 * <p>
 * This class can then be used to wrap up operations that while in progress should block tests from
 * accessing the UI.
 * </p>
 * <p/>
 * <pre>
 * {@code
 *   public interface FooServer {
 *     public Foo newFoo();
 *     public void updateFoo(Foo foo);
 *   }
 *
 *   public DecoratedFooServer implements FooServer {
 *     private final FooServer realFooServer;
 *     private final CountingIdlingResource fooServerIdlingResource;
 *
 *     public DecoratedFooServer(FooServer realFooServer,
 *         CountingIdlingResource fooServerIdlingResource) {
 *       this.realFooServer = checkNotNull(realFooServer);
 *       this.fooServerIdlingResource = checkNotNull(fooServerIdlingResource);
 *     }
 *
 *     public Foo newFoo() {
 *       fooServerIdlingResource.increment();
 *       try {
 *         return realFooServer.newFoo();
 *       } finally {
 *         fooServerIdlingResource.decrement();
 *       }
 *     }
 *
 *     public void updateFoo(Foo foo) {
 *       fooServerIdlingResource.increment();
 *       try {
 *         realFooServer.updateFoo(foo);
 *       } finally {
 *         fooServerIdlingResource.decrement();
 *       }
 *     }
 *   }
 *   }
 *   </pre>
 *
 * Then in your test setup:
 * <pre>
 *   {@code
 *     public void setUp() throws Exception {
 *       super.setUp();
 *       FooServer realServer = FooApplication.getFooServer();
 *       CountingIdlingResource countingResource = new CountingIdlingResource("FooServerCalls");
 *       FooApplication.setFooServer(new DecoratedFooServer(realServer, countingResource));
 *       Espresso.registerIdlingResource(countingResource);
 *     }
 *   }
 *   </pre>
 */
@SuppressWarnings("javadoc")
public final class CountingIdlingResource implements IdlingResource {
    private static final String TAG = "CountingIdlingResource";
    private final String resourceName;
    private final AtomicInteger counter = new AtomicInteger(0);
    private final boolean debugCounting;
    // written from main thread, read from any thread.
    // read/written from any thread - used for debugging messages.
    private volatile long becameBusyAt = 0;
    private volatile long becameIdleAt = 0;

    /**
     * Creates a CountingIdlingResource without debug tracing.
     *
     * @param resourceName the resource name this resource should report to Espresso.
     */
    public CountingIdlingResource(String resourceName) {
        this(resourceName, false);
    }

    /**
     * Creates a CountingIdlingResource.
     *
     * @param resourceName  the resource name this resource should report to Espresso.
     * @param debugCounting if true increment & decrement calls will print trace information to
     *                      logs.
     */
    public CountingIdlingResource(String resourceName, boolean debugCounting) {
        if (TextUtils.isEmpty(resourceName)) {
            throw new IllegalArgumentException("Resource name must not be empty or null");
        }
        this.resourceName = resourceName;
        this.debugCounting = debugCounting;
    }

}