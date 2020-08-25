package com.swiderski.carrental.pdfGenerator.tableConfig;

import java.util.HashMap;

public class TableConfigFactory {

    private static final HashMap<Class<?>, TableConfig> factoryTableConfig = new HashMap<>();

    public static TableConfig getTable(Class<?> clazz) {
        TableConfig tableConfig = factoryTableConfig.get(clazz);

        if (tableConfig == null) {
            tableConfig = new TableConfig(clazz);
            factoryTableConfig.put(clazz, tableConfig);
        }

        return tableConfig;
    }
}
