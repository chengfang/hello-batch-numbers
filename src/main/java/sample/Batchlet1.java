/*
 * Copyright (c) 2021 Red Hat, Inc. and/or its affiliates.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package sample;

import javax.batch.api.BatchProperty;
import javax.batch.api.Batchlet;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;

@Named
public class Batchlet1 implements Batchlet {
    @Inject
    @BatchProperty
    @Max(5)
    long seconds;

    @Inject
    @BatchProperty
    @Email
    String email;

    @Override
    public String process() throws Exception {
        long sec = seconds == 0 ? 30 : seconds;
        Thread.sleep(sec * 1000);
        System.out.printf("## finished Batchlet1.process after sleeping %s seconds%n", sec);
        System.out.printf("## email: %s%n", email);
        return "Sleep " + sec;
    }

    @Override
    public void stop() throws Exception {
        System.out.printf("## Batchlet1.stop called%n");
    }
}
