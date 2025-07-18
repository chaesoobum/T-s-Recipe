package com.csb.data.repositories

import com.csb.domain.model.Ingredient
import com.csb.domain.model.Procedure
import com.csb.domain.model.RecipeScreen
import com.csb.domain.model.Step
import com.csb.domain.repositories.RecipeRepository
import kotlinx.coroutines.delay
import javax.inject.Inject


class RecipeRepositoryImpl@Inject constructor(): RecipeRepository {
    override suspend fun fetchRecipeScreen(): RecipeScreen {

        //null이 있으면 "" 빈값으로 보내주자

        //dummy
        val recipeScreenDataClass = RecipeScreen(
            imageUrl = "https://raw.githubusercontent.com/realbeginnerr/tsrecipe/main/assets/img/r07_broccoliseasonedtofu.jpg",
            title = "브로콜리 두부 무침",
            portion = "3",
            price = "4,000",
            time = "15",
            name = "홍길동",
            memo = "브로콜리는 살짝 익히는 게 맛있음",
            ingredientList = listOf(
                Ingredient(
                    ingredientName = "브로콜리",
                    ingredientQty = 1.0,
                    ingredient_g = 200.0
                ),
                Ingredient(
                    ingredientName = "두부",
                    ingredientQty = 0.5,
                    ingredient_g = 150.0
                )
            ),
            sourceList = listOf(
                Ingredient(
                    ingredientName = "소금",
                    ingredientTbsp = 0.5
                ),
                Ingredient(
                    ingredientName = "참기름",
                    ingredientTbsp = 1.0
                ),
                Ingredient(
                    ingredientName = "다진마늘",
                    ingredientTbsp = 0.5,
                    ingredientTsp = 1.0,
                    ingredient_g = 1.0,
                    ingredientCup = 0.1,
                    ingredientQty = 1.0,
                    ingredient_ml = 10.0,
                    ingredient_oz = 2.0
                )
            ),
            stepList = listOf(
                Step(
                    stepIndex = 1,
                    stepName = "브로콜리 준비",
                    procedureList = listOf(
                        Procedure(
                            procedureTitle = "브로콜리 자르기",
                            procedureImage = "https://example.com/image1.jpg",
                            procedureContent = "브로콜리를 먹기 좋은 크기로 자릅니다."
                        ),
                        Procedure(
                            procedureTitle = "브로콜리 데치기",
                            procedureImage = "https://example.com/image2.jpg",
                            procedureContent = "끓는 물에 소금을 약간 넣고 1~2분간 데친 후 찬물에 헹궈 물기를 제거합니다."
                        )
                    )
                ),
                Step(
                    stepIndex = 2,
                    stepName = "두부 준비",
                    procedureList = listOf(
                        Procedure(
                            procedureTitle = "두부 데치기",
                            procedureImage = "https://example.com/image3.jpg",
                            procedureContent = "두부를 끓는 물에 살짝 데쳐 키친타월로 감싸 물기를 제거합니다."
                        ),
                        Procedure(
                            procedureTitle = "두부 으깨기",
                            procedureImage = "https://example.com/image4.jpg",
                            procedureContent = "두부를 손이나 칼로 으깨줍니다."
                        )
                    )
                ),
                Step(
                    stepIndex = 3,
                    stepName = "재료 버무리기",
                    procedureList = listOf(
                        Procedure(
                            procedureTitle = "양념 넣기",
                            procedureImage = "https://example.com/image5.jpg",
                            procedureContent = "브로콜리와 두부에 소금, 다진마늘, 참기름을 넣습니다."
                        ),
                        Procedure(
                            procedureTitle = "섞기",
                            procedureImage = "https://example.com/image6.jpg",
                            procedureContent = "모든 재료를 고루 섞은 뒤 기호에 따라 통깨를 뿌립니다."
                        )
                    )
                )
            )
        )


        delay(1000)

        return recipeScreenDataClass
    }
}