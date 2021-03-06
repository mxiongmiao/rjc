/*
 * Copyright 2010-2011. Evgeny Dolgov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.idevlab.rjc;

import org.idevlab.rjc.ds.SimpleDataSource;
import org.junit.After;
import org.junit.Before;


/**
 * @author Evgeny Dolgov
 */
public abstract class SingleNodeTestBase {
    protected static HostAndPortUtil.HostAndPort hnp = HostAndPortUtil.getRedisServers().get(0);

    protected Session session;
    private SessionFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = new SessionFactoryImpl(new SimpleDataSource(hnp.host, hnp.port, 2000));
        this.session = createSession();
        this.session.flushAll();
    }

    protected Session createSession() {
        Session result = factory.create();
        result.configSet("timeout", "2000");
        return result;
    }

    @After
    public void tearDown() throws Exception {
        this.session.close();
    }
}
