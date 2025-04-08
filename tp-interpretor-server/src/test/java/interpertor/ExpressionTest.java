package interpertor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.formation.ObjectMapperFactory;
import fr.formation.interpretor.Evaluation;
import fr.formation.interpretor.Interpretor;
import fr.formation.model.Photo;

class ExpressionTest {
    @Test
    void shouldReturnTitleByPhoto1() {
        // given
        String value = "get photo 1 title";

        // when
        Evaluation result = Interpretor.evaluate(value);

        // then
        Assertions.assertEquals("accusamus beatae ad facilis cum similique qui sunt", new String(result.getValue()));
    }

    @Test
    void shouldReturnIdByPhoto2() {
        // given
        String value = "GET photo 2 id";

        // when
        Evaluation result = Interpretor.evaluate(value);

        // then
        Assertions.assertEquals("2", new String(result.getValue()));
    }

    @Test
    void shouldReturnJsonByPhoto3() throws JsonProcessingException {
        // given
        String value = "get photo 3";
        Photo photo = new Photo();
        ObjectMapper mapper = ObjectMapperFactory.createObjectMapper();

        photo.setId(3);
        photo.setAlbumId(1);
        photo.setTitle("officia porro iure quia iusto qui ipsa ut modi");
        photo.setUrl("https://via.placeholder.com/600/24f355");
        photo.setThumbnailUrl("https://via.placeholder.com/150/24f355");

        // when
        Evaluation result = Interpretor.evaluate(value);

        // then
        Assertions.assertEquals(mapper.writeValueAsString(photo), new String(result.getValue()));
    }

    @Test
    void shouldReturnTitleByAlbum10() {
        // given
        String value = "get album 10 title";

        // when
        Evaluation result = Interpretor.evaluate(value);

        // then
        Assertions.assertEquals("distinctio laborum qui", new String(result.getValue()));
    }

    @Test
    void shouldReturnUserIdByAlbum5() {
        // given
        String value = "get album 5 userId";

        // when
        Evaluation result = Interpretor.evaluate(value);

        // then
        Assertions.assertEquals("1", new String(result.getValue()));
    }
    
    @ParameterizedTest
    @ValueSource(strings = { "got album 5 userId", "get alb 5 userId", "get album 5 usrId" })
    void shouldReturnDefaultValueIfWrongCommand(String value) {
        // given
        
        // when
        Evaluation result = Interpretor.evaluate(value);
        
        // then
        Assertions.assertNull(result.getValue());
        Assertions.assertEquals(-1, result.getType());
    }

}
