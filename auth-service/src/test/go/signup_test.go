package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"github.com/google/uuid"
	"net/http"
	"testing"
)

func TestSignUpValid(t *testing.T) {
	request := SignUpRequest{
		Username:   "user-" + uuid.New().String(),
		Password:   "123",
		Email:      "anton@anton.anton",
		FirstName:  "Anton",
		LastName:   "Yurkov",
		Patronymic: "Dm",
	}
	signUpUrl := "http://localhost:8222/api/v1/users/signup"

	client := &http.Client{}

	marshal, err1 := json.Marshal(request)
	if err1 != nil {
		fmt.Println("Error marshalling JSON: ", err1)
	}
	response, err := client.Post(signUpUrl, "application/json", bytes.NewBuffer(marshal))
	if err != nil {
		fmt.Print("Error sending POST request:", err)
	}

	defer response.Body.Close()

	var signUpResponse SignUpResponse
	err = json.NewDecoder(response.Body).Decode(&signUpResponse)

	if response.StatusCode != 201 {
		t.Error("Error happened")
	}

}
func TestSignUpInvalid(t *testing.T) {
	request := SignUpRequest{
		Username:   "Anton",
		Password:   "123",
		Email:      "anton@anton.anton",
		FirstName:  "Anton",
		LastName:   "Yurkov",
		Patronymic: "Dm",
	}
	signUpUrl := "http://localhost:8222/api/v1/users/signup"

	client := &http.Client{}

	marshal, err1 := json.Marshal(request)
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

	if errorResponse.Code != 400 {
		t.Error("Error happened")
	}
	if response.StatusCode != 400 {
		t.Error("Error happened")
	}

}

type SignUpRequest struct {
	Username   string `json:"username"`
	Password   string `json:"password"`
	Email      string `json:"email"`
	FirstName  string `json:"firstName"`
	LastName   string `json:"lastName"`
	Patronymic string `json:"patronymic"`
}
type SignUpResponse struct {
	Email      string `json:"email"`
	Id         string `json:"id"`
	Username   string `json:"username"`
	Password   string `json:"password"`
	Created    string `json:"created"`
	Name       string `json:"name"`
	LastName   string `json:"lastName"`
	Patronymic string `json:"patronymic"`
}
