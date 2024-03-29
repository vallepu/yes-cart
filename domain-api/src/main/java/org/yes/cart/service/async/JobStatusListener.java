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

package org.yes.cart.service.async;

import org.yes.cart.service.async.model.JobStatus;

/**
 * Listener should be created per job instance and should not be shared by
 * several threads.
 *
 * User: denispavlov
 * Date: 12-07-30
 * Time: 9:43 AM
 */
public interface JobStatusListener {

    /**
     * @return latest job status
     */
    JobStatus getLatestStatus();

    /**
     * @return unique job token
     */
    String getJobToken();

    /**
     * Ping the listener to notify job's healthy state and reset timeout.
     */
    void notifyPing();

    /**
     * Ping the listener to notify job's healthy state and reset timeout.
     * Use msg to have a current progress message (like a progress bar)
     * This message will be appended to the end of the report until a real
     * message method of this listener is invoked - then this message is removed.
     */
    void notifyPing(String msg);

    /**
     * Notify of a message (equivalent to info)
     * @param message message
     */
    void notifyMessage(String message);

    /**
     * Notify of a warning message
     * @param warning warning message
     */
    void notifyWarning(String warning);

    /**
     * Notify of an error message
     * @param error error message
     */
    void notifyError(String error);

    /**
     * Notify completion
     */
    void notifyCompleted();

    /**
     * @return true if listeners has received result
     */
    boolean isCompleted();

    /**
     * @return timeout setting in millis
     */
    long getTimeoutValue();

    /**
     * @return true if listeners last message timestamp exceed timeout
     */
    boolean isTimedOut();

}
