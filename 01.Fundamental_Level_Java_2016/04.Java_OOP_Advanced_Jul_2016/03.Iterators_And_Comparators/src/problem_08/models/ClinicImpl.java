package problem_08.models;

import problem_08.contracts.Clinic;
import problem_08.contracts.Pet;

public class ClinicImpl implements Clinic {

    private String clinicName;
    private Pet[] rooms;

    public ClinicImpl(String clinicNameArg,
                      int rooms) {
        this.setClinicName(clinicNameArg);
        this.setRooms(rooms);
    }

    @Override
    public String getClinicName() {
        return this.clinicName;
    }

    private void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    @Override
    public String print(int roomNumber) {
        if (this.rooms[roomNumber - 1] == null) {
            return "Room empty";
        }
        return (String.format("%s %d %s",
                this.rooms[roomNumber - 1].getName(),
                this.rooms[roomNumber - 1].getAge(),
                this.rooms[roomNumber - 1].getKind()));
    }

    @Override
    public String print() {
        StringBuilder builder = new StringBuilder();
        for (Pet pet : rooms) {
            if (pet == null) {
                builder.append("Room empty").
                        append(System.lineSeparator());
            } else {
                builder.append(String.format("%s %d %s%n",
                        pet.getName(),
                        pet.getAge(),
                        pet.getKind()));
            }
        }
        return builder.toString().trim();
    }

    @Override
    public boolean add(Pet pet) {
        int centralRoom = this.rooms.length / 2;
        if (this.tryAccomodatePet(pet, centralRoom)) {
            return true;
        }
        int leftHalf = centralRoom;
        int rightHalf = centralRoom;
        for (int i = 0; i < centralRoom; i++) {
            leftHalf--;
            rightHalf++;
            if (this.tryAccomodatePet(pet, leftHalf)) {
                return true;
            }
            if (this.tryAccomodatePet(pet, rightHalf)) {
                return true;
            }
        }
        return false;
    }

    private boolean tryAccomodatePet(Pet pet, int roomNumber) {
        if (this.rooms[roomNumber] == null) {
            this.rooms[roomNumber] = pet;
            return true;
        }
        return false;
    }

    @Override
    public boolean release() {
        int centralRoom = this.rooms.length / 2;
        for (int i = centralRoom; i < this.rooms.length; i++) {
            if (this.rooms[i] != null) {
                this.rooms[i] = null;
                return true;
            }
        }
        for (int i = 0; i < centralRoom; i++) {
            if (this.rooms[i] != null) {
                this.rooms[i] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasEmptyRooms() {
        if (this.rooms[this.rooms.length - 1] == null) {
            return true;
        }
        return false;
    }

    private void setRooms(int rooms) {
        if (rooms % 2 == 0) {
            throw new IllegalArgumentException("Invalid Operation!");
        }
        this.rooms = new Pet[rooms];
    }
}
