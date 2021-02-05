package main

import "fmt"

type Pet interface {
	//SetName()
	Name() string
	Category() string
}

type Dog struct {
	name string
}

func (dog Dog) Name() string {
	return dog.name
}

func (dog Dog) Category() string {
	return "dog"
}

func (dog *Dog) SetName(name string) {
	dog.name = name
}

type Animal interface {
	Pet
	Skin()
}

/*func main() {
	dog := Dog{"little pig"}
	var pet Pet = dog
	dog.SetName("monster")

	fmt.Println("pet name:", pet.Name())
}*/

func main() {
	var dog *Dog = nil
	var pet Pet
	fmt.Println("1. pet2 is nil: ", pet == nil)

	pet = dog
	fmt.Println("2. pet2 is nil: ", pet == nil)
}
