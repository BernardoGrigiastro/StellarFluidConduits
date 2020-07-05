package com.tfar.stellarfluidconduits.common.config;

import info.loenwind.autoconfig.factory.IValue;
import info.loenwind.autoconfig.factory.IValueFactory;

public class StellarFluidConduitConfig {
    private static final int MAX = 2000000000; // 0x77359400, keep some headroom to MAX_INT
    private static final int MAXIO = MAX / 2;

    public static final IValueFactory F = Config.F.section("stellarfluidconduits");

    public static final IValue<Integer> extractRate = F.make("extractRate", 12800,
            "Millibuckets per tick extracted by a stellar fluid conduit's auto extracting.").setRange(1, MAXIO).sync();
    public static final IValue<Integer> maxIO = F.make("maxIO", 51200,
            "Millibuckets per tick that can pass through a single connection to a stellar fluid conduit.").setRange(1, MAXIO).sync();
}
