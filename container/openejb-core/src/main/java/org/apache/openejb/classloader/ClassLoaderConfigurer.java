/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.openejb.classloader;

import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public interface ClassLoaderConfigurer {
    URL[] additionalURLs();
    boolean accept(final URL url);

    final class Helper {
        private Helper() {
            // no-op
        }

        public static void configure(final Collection<URL> urls, final ClassLoaderConfigurer configurer) {
            final Iterator<URL> it = urls.iterator();
            while (it.hasNext()) {
                if (!configurer.accept(it.next())) {
                    it.remove();
                }
            }
            urls.addAll(Arrays.asList(configurer.additionalURLs()));
        }
    }
}
