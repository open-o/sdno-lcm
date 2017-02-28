/*
 * Copyright 2017 Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openo.sdno.lcm.model.workplan;

import java.util.List;
import java.util.ArrayList;

/**
 * This class represents the execution result of a work plan
 */
public class WorkPlanExecutionResult {
    boolean overallResult;  //whether the whole work plan is executed successfully. true: success; false: failure
    List<WorkItem> succeededItems;  //work items that have been executed successfully.
    List<WorkItem> failedItems;  //work items that failed during their execution.
    List<WorkItem> unprocessedItems;  //work items that haven't been executed (due to fast-fail or node dependency).

    /**
     * constructor
     */
    public WorkPlanExecutionResult() {
        succeededItems = new ArrayList<WorkItem>();
        failedItems = new ArrayList<WorkItem>();
        unprocessedItems = new ArrayList<WorkItem>();
    }

    //getters
    public boolean getOverallResult() {
        return overallResult;
    }
    public List<WorkItem> getSucceededItems() {
        return succeededItems;
    }
    public List<WorkItem> getFailedItems() {
        return failedItems;
    }
    public List<WorkItem> getUnprocessedItems() {
        return unprocessedItems;
    }

    //setter & inserter
    public void setOverallResult(boolean succeedOrNot) {
        overallResult = succeedOrNot;
    }
    public void addSucceededItem(WorkItem item) {
        succeededItems.add(item);
    }
    public void addFailedItem(WorkItem item) {
        failedItems.add(item);
    }
    public void addUnprocessedItem(WorkItem item) {
        unprocessedItems.add(item);
    }
}
