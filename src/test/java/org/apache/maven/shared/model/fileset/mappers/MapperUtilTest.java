package org.apache.maven.shared.model.fileset.mappers;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.apache.maven.shared.model.fileset.Mapper;
import org.junit.Test;

/**
 * A test-case for the MapperUtil.
 * 
 * @version $Id: MapperUtilTest.java 1716893 2015-11-27 16:09:51Z khmarbaise $
 */
public class MapperUtilTest
{

    @Test
    public void getFileNameMapperShouldReturnNull()
        throws MapperException
    {
        assertNull( MapperUtil.getFileNameMapper( null ) );
    }

    @Test
    public void getFileNameMapperShouldReturnIdentityMapper()
        throws MapperException
    {
        Mapper mapper = new Mapper();
        FileNameMapper fileNameMapper = MapperUtil.getFileNameMapper( mapper );
        assertNotNull( fileNameMapper );
        assertEquals( "/var/some-file.text", fileNameMapper.mapFileName( "/var/some-file.text" ) );
    }

    @Test
    public void getFileNameMapperShouldFileNameMapperType()
        throws MapperException
    {
        // check with FileNameMapper type
        Mapper mapper = new Mapper();
        mapper.setType( "glob" );
        mapper.setFrom( "*.java" );
        mapper.setTo( "*.class" );
        FileNameMapper fileNameMapper = MapperUtil.getFileNameMapper( mapper );
        assertNotNull( fileNameMapper );
        assertEquals( "/var/SomeClasses.class", fileNameMapper.mapFileName( "/var/SomeClasses.java" ) );
    }

    @Test
    public void testGetFileNameMapper() throws MapperException
    {
        Mapper mapper = null;
        try
        {
            assertNull( MapperUtil.getFileNameMapper( mapper ) );
        }
        catch ( MapperException e )
        {
            fail( "Unexpected exception " + e );
        }
        mapper = new Mapper();
        try
        {
            // default to identity mapper.
            FileNameMapper fileNameMapper = MapperUtil.getFileNameMapper( mapper );
            assertNotNull( fileNameMapper );
            assertEquals( "/var/some-file.text", fileNameMapper.mapFileName( "/var/some-file.text" ) );
        }
        catch ( MapperException e )
        {
            fail( "Unexpected exception " + e );
        }
        // check with FileNameMapper type
        mapper = new Mapper();
        mapper.setType( "glob" );
        mapper.setFrom( "*.java" );
        mapper.setTo( "*.class" );

        FileNameMapper fileNameMapper = MapperUtil.getFileNameMapper( mapper );
        assertNotNull( fileNameMapper );
        assertEquals( "/var/SomeClasses.class", fileNameMapper.mapFileName( "/var/SomeClasses.java" ) );
    }
}
