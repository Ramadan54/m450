package ch.tbz.recipe.planner.mapper;

import ch.tbz.recipe.planner.domain.Ingredient;
import ch.tbz.recipe.planner.domain.Recipe;
import ch.tbz.recipe.planner.domain.Unit;
import ch.tbz.recipe.planner.entities.IngredientEntity;
import ch.tbz.recipe.planner.entities.RecipeEntity;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

/**
 * Testet die beiden MapStruct-Mapper (Recipe und Ingredient) fuer beide Richtungen.
 * Verwendet SoftAssertions: es werden ALLE Felder geprueft und alle Fehler
 * gesammelt am Ende ausgegeben, statt beim ersten Fehler abzubrechen.
 */
@SpringBootTest
class MapperTest {

    @Autowired
    private RecipeEntityMapper recipeEntityMapper;

    @Autowired
    private IngredientEntityMapper ingredientEntityMapper;

    @Test
    void ingredientEntityToDomain_mapsAllFields() {
        UUID id = UUID.randomUUID();
        IngredientEntity entity = new IngredientEntity(id, "Tomato", "The big ones", Unit.PIECE, 5);

        Ingredient domain = ingredientEntityMapper.entityToDomain(entity);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(domain.getId()).as("id").isEqualTo(id);
        softly.assertThat(domain.getName()).as("name").isEqualTo("Tomato");
        softly.assertThat(domain.getComment()).as("comment").isEqualTo("The big ones");
        softly.assertThat(domain.getUnit()).as("unit").isEqualTo(Unit.PIECE);
        softly.assertThat(domain.getAmount()).as("amount").isEqualTo(5);
        softly.assertAll();
    }

    @Test
    void ingredientDomainToEntity_mapsAllFields() {
        UUID id = UUID.randomUUID();
        Ingredient domain = new Ingredient(id, "Onion", "Red onion", Unit.GRAMM, 200);

        IngredientEntity entity = ingredientEntityMapper.domainToEntity(domain);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(entity.getId()).as("id").isEqualTo(id);
        softly.assertThat(entity.getName()).as("name").isEqualTo("Onion");
        softly.assertThat(entity.getComment()).as("comment").isEqualTo("Red onion");
        softly.assertThat(entity.getUnit()).as("unit").isEqualTo(Unit.GRAMM);
        softly.assertThat(entity.getAmount()).as("amount").isEqualTo(200);
        softly.assertAll();
    }

    @Test
    void recipeEntityToDomain_mapsAllFields() {
        UUID recipeId = UUID.randomUUID();
        UUID ingredientId = UUID.randomUUID();
        IngredientEntity ingredientEntity =
                new IngredientEntity(ingredientId, "Tomato", "The big ones", Unit.PIECE, 5);
        RecipeEntity entity = new RecipeEntity(recipeId, "Lasagne", "Very tasty",
                "http://example.com/img.jpg", List.of(ingredientEntity));

        Recipe domain = recipeEntityMapper.entityToDomain(entity);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(domain.getId()).as("id").isEqualTo(recipeId);
        softly.assertThat(domain.getName()).as("name").isEqualTo("Lasagne");
        softly.assertThat(domain.getDescription()).as("description").isEqualTo("Very tasty");
        softly.assertThat(domain.getImageUrl()).as("imageUrl").isEqualTo("http://example.com/img.jpg");
        softly.assertThat(domain.getIngredients()).as("ingredients size").hasSize(1);
        softly.assertThat(domain.getIngredients().get(0).getName())
                .as("first ingredient name").isEqualTo("Tomato");
        softly.assertAll();
    }

    @Test
    void recipeDomainToEntity_mapsAllFields() {
        UUID recipeId = UUID.randomUUID();
        UUID ingredientId = UUID.randomUUID();
        Ingredient ingredient =
                new Ingredient(ingredientId, "Cheese", "Grated", Unit.GRAMM, 100);
        Recipe domain = new Recipe(recipeId, "Pizza", "Cheesy",
                "http://example.com/pizza.jpg", List.of(ingredient));

        RecipeEntity entity = recipeEntityMapper.domainToEntity(domain);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(entity.getId()).as("id").isEqualTo(recipeId);
        softly.assertThat(entity.getName()).as("name").isEqualTo("Pizza");
        softly.assertThat(entity.getDescription()).as("description").isEqualTo("Cheesy");
        softly.assertThat(entity.getImageUrl()).as("imageUrl").isEqualTo("http://example.com/pizza.jpg");
        softly.assertThat(entity.getIngredients()).as("ingredients size").hasSize(1);
        softly.assertThat(entity.getIngredients().get(0).getName())
                .as("first ingredient name").isEqualTo("Cheese");
        softly.assertAll();
    }
}