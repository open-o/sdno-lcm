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

import java.util.ArrayList;
import java.util.List;

import org.openo.sdno.lcm.exception.LcmInternalException;

/**
 * This class represents a work plan
 */
public class WorkPlan {

    /**
     * work items that compose a work plan. Note: We should execute from the
     * head to the tail of this list.
     */
    List<WorkItem> workItems;

    // constructor
    public WorkPlan() {
        workItems = new ArrayList<>();
    }

    /**
     * append one work item to the end of the list
     * 
     * @param item
     *            the work item to be appended to the end of this work plan.
     */
    public void insert(WorkItem item) {
        workItems.add(item);
    }

    /**
     * get the number of work items in the planwork item by index.
     * 
     * @return the number of work item in the plan
     */
    public int size() {
        return workItems.size();
    }

    /**
     * get work item by index.
     * 
     * @return work item at the given index. LcmInternalException will be thrown
     *         if invalid index is given.
     */
    public WorkItem getWorkItem(int index) {
        if (index < 0 || index > workItems.size()) {
            throw new LcmInternalException("No workitem exists with index " + index);
        }
        return workItems.get(index);
    }
}