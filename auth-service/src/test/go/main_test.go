package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"net/http"
	"testing"
)

type SignInRequest struct {
	Username string `json:"username"`
	Password string `json:"password"`
}

type Tokens struct {
	AccessToken struct {
		Value string `json:"value"`
	} `json:"accessToken"`
	RefreshToken struct {
		Value string `json:"value"`
	} `json:"refreshToken"`
}

func TestGeneratetokensValid(t *testing.T) {

	signInUrl := "http://localhost:8222/api/v1/tokens/generate"

	user := SignInRequest{
		Username: "Anton",
		Password: "123",
	}

	client := &http.Client{}

	marshal, err1 := json.Marshal(user)
	if err1 != nil {
		fmt.Println("Error marshalling JSON: ", err1)
	}
	response, err := client.Post(signInUrl, "application/json", bytes.NewBuffer(marshal))
	if err != nil {
		fmt.Print("Error sending POST request:", err)
	}

	defer response.Body.Close()

	var tokens Tokens
	err = json.NewDecoder(response.Body).Decode(&tokens)

	if tokens.AccessToken.Value == "" || tokens.RefreshToken.Value == "" {

	}

}

func TestGenerateTokensInvalid(t *testing.T) {
	signInUrl := "http://localhost:8222/api/v1/tokens/generate"

	user := SignInRequest{
		Username: "Antonqwertyu",
		Password: "123",
	}

	client := &http.Client{}

	marshal, err1 := json.Marshal(user)
	if err1 != nil {
		fmt.Println("Error marshalling JSON: ", err1)
	}
	response, err := client.Post(signInUrl, "application/json", bytes.NewBuffer(marshal))
	if err != nil {
		fmt.Print("Error sending POST request:", err)
	}

	defer response.Body.Close()

	var errorResponse ErrorResponse
	err = json.NewDecoder(response.Body).Decode(&errorResponse)

	if errorResponse.Code == 404 && errorResponse.Message != "" && errorResponse.Path != "" && errorResponse.Timestamp != "" {

	}

	if response.StatusCode != 400 {
		t.Error("Something wrong with response")
	}
}

type ErrorResponse struct {
	Message   string `json:"message"`
	Path      string `json:"path"`
	Code      int    `json:"code"`
	Timestamp string `json:"timestamp"`
}
