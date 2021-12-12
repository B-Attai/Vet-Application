import React, { useEffect, useState } from "react";
import { SERVER_URL } from "../constants.js";
import ProfileCard from "./ProfileCard";
import Grid from "@mui/material/Grid";
import Container from "@mui/material/Container";
import { useHistory } from "react-router-dom";

export default function ProfileGrid() {
  const [animals, setAnimals] = useState([]);

  function hasPhoto(obj) {
    return obj.animalPhoto.length === 0 ? false : true;
  }

  useEffect(() => {
    fetch(SERVER_URL + "animals")
      .then((response) => response.json())
      .then((data) => {
        // animals: responseData
        const animalData = data.map((a) => {
          let imagePath = "";
          hasPhoto(a)
            ? (imagePath = `/images/${a.animalPhoto[0].theFile}`)
            : (imagePath = `/images/animals.jpg`);
          return {
            animalId: a.animalId,
            animalName: a.animalName,
            tattooNum: a.tattooNum,
            breed: a.breed + " " + a.species,
            image: imagePath,
          };
        });
        setAnimals(animalData);
      })
      .catch((err) => console.error(err));
  }, []);

  //
  const history = useHistory();

  const addViewProfileHandler = (profileID) => {
    console.log(profileID);
    history.push({ pathname: "/animal-profile", state: profileID });
  };

  return (
    <div>
      <Container sx={{ p: 2 }}>
        <Grid container spacing={4}>
          {animals.map((a) => {
            return (
              <Grid item xs={12} sm={6} md={4}>
                <ProfileCard
                  sendAnimal={addViewProfileHandler}
                  id={a.animalId}
                  img={a.image}
                  name={a.animalName}
                  tattooNum={a.tattooNum}
                  breed={a.breed}
                />
              </Grid>
            );
          })}
        </Grid>
      </Container>
    </div>
  );
}
