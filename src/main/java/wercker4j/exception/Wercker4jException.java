/*
 * Copyright 2016 yeahlol
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

package wercker4j.exception;

import java.io.Serializable;

/**
 * @author yeahlol
 */
public class Wercker4jException extends Exception implements Serializable {
    private static final long serialVersionUID = -6099579442463238537L;

    private int code = -1;

    public Wercker4jException(String message) {
        super(message);
    }

    public Wercker4jException(Exception e) {
        super(e);
    }

    public Wercker4jException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
