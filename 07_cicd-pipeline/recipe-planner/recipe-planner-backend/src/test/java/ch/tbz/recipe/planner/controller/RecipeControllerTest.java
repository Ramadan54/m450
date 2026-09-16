package ch.tbz.recipe.planner.controller;

import ch.tbz.recipe.planner.domain.Ingredient;
import ch.tbz.recipe.planner.domain.Recipe;
import ch.tbz.recipe.planner.domain.Unit;
import ch.tbz.recipe.planner.mapper.RecipeEntityMapper;
import ch.tbz.recipe.planner.repository.RecipeRepository;
import ch.tbz.recipe.planner.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Testet die Web-Schicht des RecipeControllers mit MockMvc.
 * Der RecipeService wird gemockt, damit nur der Controller isoliert getestet wird.
 */
@WebMvcTest(RecipeController.class)
class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RecipeService recipeService;

    // Wird vom Controller im Konstruktor verlangt, daher als Mock nötig.
    @MockBean
    private RecipeEntityMapper recipeEntityMapper;

    // Wird von der init-Methode in RecipePlannerApplication verlangt,
    // damit der Test-Kontext starten kann.
    @MockBean
    private RecipeRepository recipeRepository;

    private Recipe sampleRecipe;
    private UUID recipeId;

    @BeforeEach
    void setUp() {
        recipeId = UUID.randomUUID();
        Ingredient tomato = new Ingredient(UUID.randomUUID(), "Tomato", "The big ones", Unit.PIECE, 5);
        sampleRecipe = new Recipe(recipeId, "Lasagne al Forno", "Very tasty",
                "http://example.com/img.jpg", List.of(tomato));
    }

    @Test
    void getRecipes_returnsListOfRecipes() throws Exception {
        when(recipeService.getRecipes()).thenReturn(List.of(sampleRecipe));

        mockMvc.perform(get("/api/recipes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Lasagne al Forno"))
                .andExpect(jsonPath("$[0].ingredients[0].name").value("Tomato"));
    }

    @Test
    void getRecipe_returnsSingleRecipe() throws Exception {
        when(recipeService.getRecipeById(recipeId)).thenReturn(sampleRecipe);

        mockMvc.perform(get("/api/recipes/recipe/{recipeId}", recipeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(recipeId.toString()))
                .andExpect(jsonPath("$.name").value("Lasagne al Forno"));
    }

    @Test
    void addRecipe_returnsCreatedRecipe() throws Exception {
        when(recipeService.addRecipe(any(Recipe.class))).thenReturn(sampleRecipe);

        mockMvc.perform(post("/api/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleRecipe)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Lasagne al Forno"));
    }
}