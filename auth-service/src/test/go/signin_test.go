package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"github.com/google/uuid"
	"net/http"
	"testing"
)

func TestSignIn(t *testing.T) {
	signInRequest := SignInRequest{
		Username: "Anton",
		Password: "123",
	}
	signUpUrl := "http://localhost:8222/api/v1/users/signin"

	client := &http.Client{}

	marshal, err1 := json.Marshal(signInRequest)
	if err1 != nil {
		fmt.Println("Error marshalling JSON: ", err1)
	}
	response, err := client.Post(signUpUrl, "application/json", bytes.NewBuffer(marshal))
	if err != nil {
		fmt.Print("Error sending POST request:", err)
	}

	defer response.Body.Close()

	var signInResponse SignInResponse
	err = json.NewDecoder(response.Body).Decode(&signInResponse)

	if response.StatusCode != 200 {
		t.Error("Error happened")
	}
}
func TestSignInInvalid(t *testing.T) {
	signInRequest := SignInRequest{
		Username: "Anton-" + uuid.New().String(),
		Password: "123",
	}
	signUpUrl := "http://localhost:8222/api/v1/users/signin"

	client := &http.Client{}

	marshal, err1 := json.Marshal(signInRequest)
	if err1 != nil {
		fmt.Println("Error marshalling JSON: ", err1)
	}
	response, err := client.Post(signUpUrl, "application/json", bytes.NewBuffer(marshal))
	if err != nil {
		fmt.Print("Error sending POST request:", err)
	}

	defer response.Body.Close()

	var errorResponse ErrorResponse
	err = json.NewDecoder(response.Body).Decode(&errorResponse)

	if response.StatusCode != 400 {
		t.Error("Error happened")
	}
	if errorResponse.Code != 404 {
		t.Error("Error happened")

	}
}

type SignInResponse struct {
	Email      interface{} `json:"email"`
	Id         string      `json:"id"`
	Username   string      `json:"username"`
	Password   string      `json:"password"`
	Created    interface{} `json:"created"`
	Name       interface{} `json:"name"`
	LastName   interface{} `json:"lastName"`
	Patronymic interface{} `json:"patronymic"`
}
