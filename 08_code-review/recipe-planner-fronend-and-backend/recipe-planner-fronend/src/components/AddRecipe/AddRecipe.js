import React, {useState} from 'react';
import './AddRecipe.css';
import {Form, Button, Col, Row, Alert} from 'react-bootstrap';
import {useNavigate} from "react-router-dom";

import axios from "axios";
import AddIngredient from "../AddIngredient/AddIngredient";

const baseURL = "http://localhost:8080/api/recipes";

function AddRecipe() {
    const [formData, setFormData] = useState({
        "name": '',
        "description": '',
        "imageUrl": '',
        "ingredients": [],
        "id": null
    })

    const [listId, setListId] = useState(1)
    const [error, setError] = useState('')
    const navigate = useNavigate()

    const handleChange = (event) => {
        setFormData({...formData, [event.target.name]: event.target.value})
    }

    const addIngredient = () => {

        setFormData(({...formData, ingredients: [
                ...formData.ingredients, {
                    listId: listId,
                    name: '',
                    unit: 'PIECE',
                    amount: ''
                }
            ]}))
        setListId(listId + 1)

    }
    const updateIngredient = (ingredientObj) => {
        const updatedIngredients = formData.ingredients.map((ingredient) => {
            if (ingredient.listId === ingredientObj.listId) {
                return ingredientObj
            }
            return ingredient
        })
        setFormData({...formData, ingredients: updatedIngredients})
    }

    const removeIngredient = (ingredientObj) => {
        const updatedIngredients = formData.ingredients.filter((ingredient) => ingredient.listId !== ingredientObj.listId)
        setFormData({...formData, ingredients: updatedIngredients})
    }

    const validate = () => {
        if (formData.name.trim() === '') {
            return 'Please enter a recipe name.'
        }
        const invalidIngredient = formData.ingredients.find((ingredient) =>
            ingredient.name.trim() === '' || !(Number(ingredient.amount) > 0)
        )
        if (invalidIngredient) {
            return 'Every ingredient needs a name and a quantity greater than 0.'
        }
        return ''
    }

    const handleSubmit = (event) => {
        event.preventDefault()

        const validationError = validate()
        if (validationError) {
            setError(validationError)
            return
        }

        // listId is only used in the frontend, the backend expects name, unit and amount
        const recipe = {
            name: formData.name.trim(),
            description: formData.description.trim(),
            imageUrl: formData.imageUrl.trim(),
            ingredients: formData.ingredients.map(({name, unit, amount}) => ({
                name: name.trim(),
                unit: unit,
                amount: Number(amount)
            }))
        }

        axios.post(baseURL, recipe)
            .then(() => navigate('/'))
            .catch(() => setError('The recipe could not be saved. Is the backend running?'))
    }

    const renderIngredients = formData.ingredients.map(ingredient => <AddIngredient
        key={ingredient.listId}
        ingredient={ingredient}
        updateIngredient={updateIngredient}
        removeIngredient={removeIngredient}
    />)

    return (
        <>
            <div className="bg">
                <Form className="m-3" style={{maxWidth: 'none'}} onSubmit={handleSubmit} noValidate>
                    <h1 className="h3 bg-dark text-bg-primary mt-2">Add Recipe</h1>
                    {error && <Alert variant="danger">{error}</Alert>}
                    <Form.Group className="mb-1" controlId="formBasicName">
                        <Form.Label>Recipe Name:</Form.Label>
                        <Form.Control placeholder="Name" name="name" value={formData.name} onChange={handleChange}/>
                    </Form.Group><Form.Group className="mb-1" controlId="formBasicDescription">
                        <Form.Label>Description:</Form.Label>
                        <Form.Control placeholder="Description" name="description" value={formData.description} onChange={handleChange}/>
                    </Form.Group><Form.Group className="mb-1 mb-5" controlId="formBasicImageUrl">
                        <Form.Label>Image URL:</Form.Label>
                        <Form.Control placeholder="URL" name="imageUrl" value={formData.imageUrl} onChange={handleChange}/>
                    </Form.Group>
                    <Row>
                        <Col>Ingredient</Col>
                        <Col>Unit</Col>
                        <Col>Quantity</Col>
                        <Col xs={1}></Col>
                    </Row>
                    <hr/>
                    <Row>
                        <br></br>
                    </Row>
                    {renderIngredients}
                    <Row>
                        <br></br>
                        <Button
                            variant='warning'
                            onClick={addIngredient}
                            className="mt-1"
                            >Add Ingredient</Button>
                    </Row>
                    <Button variant="primary"  type="submit" className="mb-5">
                        Submit
                    </Button>
                </Form>

            </div>

        </>
    )
}

export default AddRecipe;
