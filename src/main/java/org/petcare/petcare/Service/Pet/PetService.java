//package org.petcare.petcare.Service.Pet;
//
//import lombok.RequiredArgsConstructor;
//import org.petcare.petcare.Exeption.ResourceNotFoundException;
//import org.petcare.petcare.Models.Pet;
//import org.petcare.petcare.Repository.petRepository;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//@Service
//@RequiredArgsConstructor
//public class PetService implements IPetService {
//    private final petRepository petRepository;
//    @Override
//    public List<Pet> getAllPets() {
//
//        return petRepository.findAll();
//    }
//
//    @Override
//    public Pet getPetById(Long id) {
//        return petRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + id));
//    }
//
//    @Override
//    public Pet addPet(Pet pet) {
//        return petRepository.save(pet);
//
//    }
//
//    @Override
//    public Pet updatePet(Long id, Pet pet) {
//        Pet existingPet = getPetById(id);
//        existingPet.setAge(pet.getAge());
//        existingPet.setName(pet.getName());
//        existingPet.setGender(pet.getGender());
//        existingPet.setBreed(pet.getBreed());
//        existingPet.setWeight(pet.getWeight());
//        return petRepository.save(existingPet);
//    }
//
//    @Override
//    public void deletePet(Long id) {
//        Pet pet = getPetById(id);
//        petRepository.delete(pet);
//
//    }
//
//    @Override
//    public List<Pet> saveAll(List<Pet> pets) {
//        return petRepository.saveAll(pets);
//    }
//}
