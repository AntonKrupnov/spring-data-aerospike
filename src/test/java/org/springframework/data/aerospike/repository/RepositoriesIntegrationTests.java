package org.springframework.data.aerospike.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositoriesIntegrationTests extends BaseRepositoriesIntegrationTests {

    @Autowired
    CompositeObjectRepository repository;

    @Test
    public void findOne_shouldReturnNullForNonExistingKey() throws Exception {
        CompositeObject one = repository.findOne("non-existing-id");

        assertThat(one).isNull();
    }

    @Test
    public void shouldSaveObjectWithPersistenceConstructorThatHasAllFields() throws Exception {
        CompositeObject expected = CompositeObject.builder()
                .id("composite-object-1")
                .intValue(15)
                .simpleObject(SimpleObject.builder().property1("prop1").property2(555).build())
                .build();
        repository.save(expected);

        CompositeObject actual = repository.findOne(expected.getId());

        assertThat(actual).isEqualTo(expected);
    }


}
