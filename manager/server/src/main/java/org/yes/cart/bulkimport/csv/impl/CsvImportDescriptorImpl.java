/*
 * Copyright 2009 Denys Pavlov, Igor Azarnyi
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.yes.cart.bulkimport.csv.impl;

import org.apache.commons.lang.StringUtils;
import org.yes.cart.bulkimport.csv.CsvImportColumn;
import org.yes.cart.bulkimport.csv.CsvImportDescriptor;
import org.yes.cart.bulkimport.csv.CsvImportFile;
import org.yes.cart.bulkimport.model.FieldTypeEnum;
import org.yes.cart.bulkimport.model.ImportColumn;
import org.yes.cart.bulkimport.model.ImportContext;
import org.yes.cart.util.ShopCodeContext;

import java.io.Serializable;
import java.util.*;

/**
 * User: Igor Azarny iazarny@yahoo.com
 * Date: 11/27/11
 * Time: 12:06 PM
 */
public class CsvImportDescriptorImpl implements CsvImportDescriptor, Serializable {

    private CsvImportFile importFileDescriptor;

    private Collection<CsvImportColumn> importColumns;

    private ImportColumn pkColumn;
    private Map<String, ImportColumn> columnByName;
    private Map<FieldTypeEnum, List<ImportColumn>> columnsByType;

    private String importDirectory;

    private ImportMode mode;
    private ImportContext context;
    private String entityType;
    private Class entityTypeClass;

    private String selectSql;
    private String insertSql;
    private String deleteSql;

    private boolean initialised = false;

    /**
     * Default constructor.
     */
    public CsvImportDescriptorImpl() {
        importFileDescriptor = new CsvImportFileImpl();
        importColumns = new ArrayList<CsvImportColumn>();
        mode = ImportMode.MERGE;
        context = new CsvImportContextImpl();
    }

    /** {@inheritDoc} */
    public ImportMode getMode() {
        if (mode == null) {
            mode = ImportMode.MERGE;
        }
        return mode;
    }

    /**
     * @param mode import mode
     */
    public void setMode(final ImportMode mode) {
        if (mode == null) {
            throw new IllegalArgumentException("Invalid import mode");
        }
        this.mode = mode;
    }

    /** {@inheritDoc} */
    public String getModeName() {
        return mode.name();
    }

    /**
     * @param mode import mode name
     */
    public void setModeName(final String mode) {
        try {
            this.mode = ImportMode.valueOf(mode);
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException("Invalid import mode", iae);
        }
    }

    /** {@inheritDoc} */
    public ImportContext getContext() {
        if (context == null) {
            context = new CsvImportContextImpl();
        }
        return context;
    }

    /**
     * @param context import context
     */
    public void setContext(final ImportContext context) {
        this.context = context;
    }

    /** {@inheritDoc} */
    public String getEntityType() {
        return entityType;
    }

    /** {@inheritDoc} */
    public Class getEntityTypeClass() {
        if (entityTypeClass == null) {
            if (StringUtils.isNotBlank(entityType)) {
                try {
                    entityTypeClass = Class.forName(entityType);
                } catch (ClassNotFoundException e) {
                    ShopCodeContext.getLog(this).error("Unable to work out entity type for descriptor {}", this);
                    entityTypeClass = Object.class;
                }
            } else {
                ShopCodeContext.getLog(this).error("Entity type is not specified for descriptor {}", this);
                entityTypeClass = Object.class;
            }
        }
        return entityTypeClass;
    }

    /**
     * @param entityInterface entity interface for factory
     */
    public void setEntityType(final String entityInterface) {
        this.entityType = entityInterface;
    }

    /** {@inheritDoc} */
    public String getSelectSql() {
        return selectSql;
    }

    /**
     * @param selectSql select SQL to lookup existing records
     */
    public void setSelectSql(final String selectSql) {
        this.selectSql = selectSql;
    }

    /** {@inheritDoc} */
    public String getInsertSql() {
        return insertSql;
    }

    /**
     * @param insertSql insert SQL for quick and dirty inserts
     */
    public void setInsertSql(final String insertSql) {
        this.insertSql = insertSql;
    }

    /** {@inheritDoc} */
    public String getDeleteSql() {
        return deleteSql;
    }

    /**
     * @param deleteSql delete SQL used for DELETE mode
     */
    public void setDeleteSql(final String deleteSql) {
        this.deleteSql = deleteSql;
    }

    ImportColumn getPrimaryKeyColumn() {
        if (!initialised) {
            this.reloadMappings();
        }
        return pkColumn;
    }

    /**
     * {@inheritDoc}
     */
    public CsvImportFile getImportFileDescriptor() {
        return importFileDescriptor;
    }

    /**
     * Set the {@link org.yes.cart.bulkimport.model.ImportFile}
     * for more details.
     *
     * @param importFileDescriptor import file descriptor.
     */
    protected void setImportFileDescriptor(CsvImportFile importFileDescriptor) {
        this.importFileDescriptor = importFileDescriptor;
    }

    /**
     * {@inheritDoc}
     */
    public Collection<ImportColumn> getImportColumns() {
        if (!initialised) {
            this.reloadMappings();
        }
        if (importColumns == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableCollection((Collection) importColumns);
    }

    /**
     * {@inheritDoc}
     */
    public ImportColumn getImportColumn(final String columnName) {
        if (!initialised) {
            this.reloadMappings();
        }
        return columnByName.get(columnName);
    }

    /**
     * {@inheritDoc}
     */
    public Collection<ImportColumn> getImportColumns(FieldTypeEnum fieldType) {
        if (!initialised) {
            this.reloadMappings();
        }
        final Collection<ImportColumn> cols = columnsByType.get(fieldType);
        if (cols == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableCollection(cols);
    }

    /**
     * Set the collection of import columns.
     *
     * @param importColumns collection of import columns to set.
     */
    protected void setImportColumns(Collection<CsvImportColumn> importColumns) {
        this.importColumns = new ArrayList<CsvImportColumn>();
        this.importColumns.addAll(importColumns);
        this.reloadMappings();
    }


    /**
     * {@inheritDoc}
     */
    public String getImportDirectory() {
        return importDirectory;
    }

    /**
     * Set the import folder.
     *
     * @param importDirectory import folder to use.
     */
    public void setImportDirectory(final String importDirectory) {
        this.importDirectory = importDirectory;
    }

    private void reloadMappings() {
        initialised = true;
        pkColumn = null;
        columnByName = new HashMap<String, ImportColumn>();
        columnsByType = new HashMap<FieldTypeEnum, List<ImportColumn>>();

        for (ImportColumn importColumn : importColumns) {
            List<ImportColumn> byType = columnsByType.get(importColumn.getFieldType());
            if (byType == null) {
                byType = new ArrayList<ImportColumn>();
                columnsByType.put(importColumn.getFieldType(), byType);
            }
            byType.add(importColumn);
            if (pkColumn == null && importColumn.getLookupQuery() != null && importColumn.getFieldType() == FieldTypeEnum.FIELD) {
                pkColumn = importColumn;
            }
            if (importColumn.getName() != null && importColumn.getName().length() > 0) {
                columnByName.put(importColumn.getName(), importColumn);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return "CsvImportDescriptorImpl{" +
                "importFileDescriptor=" + importFileDescriptor +
                ", importColumns=" + importColumns +
                ", pkColumn=" + pkColumn +
                ", columnByName=" + columnByName +
                ", columnsByType=" + columnsByType +
                ", importDirectory='" + importDirectory + '\'' +
                ", entityType='" + entityType + '\'' +
                ", selectSql='" + selectSql + '\'' +
                ", insertSql='" + insertSql + '\'' +
                ", initialised=" + initialised +
                '}';
    }
}