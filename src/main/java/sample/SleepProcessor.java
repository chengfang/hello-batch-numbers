/*
 * Copyright (c) 2018 Red Hat, Inc. and/or its affiliates.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package sample;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

@Named
public class SleepProcessor implements ItemProcessor {
    @Override
    public Object processItem(final Object o) throws Exception {
        Thread.sleep(2 * 1000);

        return o;
    }
}
