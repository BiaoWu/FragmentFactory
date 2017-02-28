/*
 * Copyright (C) 2017 BiaoWu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.biao.fragment.factory;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;

/**
 * @author biaowu.
 */
public class FragmentFactory {
  private static final String TAG = "FragmentFactory";

  private FragmentFactory() {
    //no instance
  }

  private static final ArrayMap<String, FragmentProvider> providers = new ArrayMap<>();
  private static FragmentProvider emptyFragmentProvider;

  public static void setEmptyFragmentProvider(FragmentProvider emptyFragmentProvider) {
    FragmentFactory.emptyFragmentProvider = emptyFragmentProvider;
  }

  public static void register(String key, FragmentProvider provider) {
    if (TextUtils.isEmpty(key)) {
      throw new IllegalArgumentException("key can not be empty");
    }
    if (provider == null) {
      throw new IllegalArgumentException("provider can not be null");
    }
    if (providers.get(key) != null) {
      throw new IllegalArgumentException("provider already register for key " + key);
    }

    providers.put(key, provider);
  }

  public static FragmentProvider get(String key) {
    FragmentProvider provider = providers.get(key);
    if (provider == null) {
      Log.e("TAG", "can not found provider for key " + key);
      provider = emptyFragmentProvider;
    }
    return provider;
  }
}
