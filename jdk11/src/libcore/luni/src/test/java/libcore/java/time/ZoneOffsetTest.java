/*
 * Copyright (C) 2017 The Android Open Source Project
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
package libcore.java.time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.UnsupportedTemporalTypeException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Additional tests for {@link ZoneOffset}.
 *
 * @see tck.java.time.TCKZoneOffset
 * @see test.java.time.TestZoneOffset
 */
@RunWith(JUnit4.class)
public class ZoneOffsetTest {

    private static final ZoneOffset OFFSET_P1 = ZoneOffset.ofHours(1);

    @Test
    public void test_isSupported() {
        for (ChronoField field : ChronoField.values()) {
            // Only OFFSET_SECONDS is supported.
            assertEquals("isSupported(" + field + ")",
                    field == ChronoField.OFFSET_SECONDS, OFFSET_P1.isSupported(field));
        }
    }

    @Test
    public void test_range() {
        assertEquals(ChronoField.OFFSET_SECONDS.range(),
                OFFSET_P1.range(ChronoField.OFFSET_SECONDS));
    }

  @Test
  public void test_range_null() {
    assertThrows(NullPointerException.class, () -> OFFSET_P1.range(null));
    }

    @Test
    public void test_range_unsupported() {
        for (ChronoField field : ChronoField.values()) {
            // Only OFFSET_SECONDS is supported.
            if (field == ChronoField.OFFSET_SECONDS) {
                continue;
            }
            try {
                OFFSET_P1.range(field);
                fail("ZoneOffset.range(" + field + ") should have failed.");
            } catch (UnsupportedTemporalTypeException expected) {
            }
        }
    }
}
