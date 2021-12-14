import React, { useEffect, useState } from "react";
import { SERVER_URL } from "../constants.js";
import { Box, Button } from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";
import { Link } from "react-router-dom"; //added
import authToken from "../authentication/DataService";
import AlertDialog from "./UserDeleteDialog";
import auth from "../authentication/AuthenticationService";

export default function UsersList() {
  const [colDefs] = useState([
    { field: "id", headerName: "User ID", width: 200 },
    { field: "username", headerName: "Username", width: 350 },
    // { field: "theType", headerName: "Type", width: 350 },
    { field: "email", headerName: "Email", width: 350 },
    { field: "activationDate", headerName: "Activation Date", width: 350 },
    { field: "active", headerName: "Status", width: 350 },
  ]);

  const [rowData, setRowData] = useState([]);
  const [data, setData] = useState("");
  const [selectedUser, setSelectedUser] = useState([]);
  const [filteredData, setFilteredData] = useState([null]);

  var selectedRowData = 0;

  useEffect(() => {
    const user = auth.getCurrentUser();

    (async () => {
      fetch(SERVER_URL + "users/", { headers: authToken() })
        .then((response) => response.json())
        .then((rowData) => {
          let userData;
          if (user.roles.includes("ROLE_TEACHING_TECHNICIAN")) {
            const test = rowData.filter(
              (u) => u.roles[0].name === "ROLE_STUDENT"
            );

            console.log(test);
            userData = test.map((u) => {
              // setFilteredData(u.roles[0]);
              // setFilteredData((state) => {
              //   return state;
              // });
              console.log(u.roles[0].name);
              // console.log(JSON.stringify(u.roles[0]["name"]));
              // console.log(u);
              return {
                id: u.id,
                username: u.username,
                // theType: u.theType,
                email: u.email,
                activationDate: u.activationDate,
                active: u.active,
              };
            });
          } else {
            userData = rowData.map((u) => {
              return {
                id: u.id,
                username: u.username,
                email: u.email,
                activationDate: u.activationDate,
                active: u.active,
              };
            });
          }
          setRowData(userData);
        })
        .catch((err) => console.error(err));
    })();
  }, [selectedUser]);

  // if (user.roles.includes("ROLE_TEACHING_TECHNICIAN")) {
  //   console.log(u.roles[0]);
  //   if (u.roles[0] === "ROLE_STUDENT") {

  // useEffect(() => {
  //   const user = auth.getCurrentUser();

  //   (async () => {
  //     fetch(SERVER_URL + "users/", { headers: authToken() })
  //       .then((response) => response.json())
  //       .then((rowData) => {
  //         const userData = rowData.map((u) => {
  //           if (user.roles.includes("ROLE_TEACHING_TECHNICIAN")) {
  //             console.log(u.roles[0]);
  //             if (u.roles[0] === "ROLE_STUDENT") {
  //               return {
  //                 id: u.id,
  //                 username: u.username,
  //                 // theType: u.theType,
  //                 email: u.email,
  //                 activationDate: u.activationDate,

  //                 active: u.active ? "Active" : "Blocked",
  //               };
  //             } else {
  //               return {
  //                 id: u.id,
  //                 username: "Access Denied",
  //                 // theType: u.theType,
  //                 email: "Access Denied",
  //                 activationDate: "Access Denied",

  //                 active: "Access Denied",
  //               };
  //             }
  //           } else {
  //             return {
  //               id: u.id,
  //               username: u.username,
  //               // theType: u.theType,
  //               email: u.email,
  //               activationDate: u.activationDate,

  //               active: u.active ? "Active" : "Blocked",
  //             };
  //           }
  //         });
  //         setRowData(userData);
  //       })
  //       .catch((err) => console.error(err));
  //   })();
  // }, [selectedUser]);

  return (
    <div style={{ height: 700, width: "100%" }}>
      <Box sx={{ display: "flex", justifyContent: "flex-end", p: 1, m: 1 }}>
        <Button
          component={Link}
          to="/users/new"
          variant="contained"
          color="success"
          sx={{ m: 1 }}
        >
          Add New User
        </Button>{" "}
        <Button
          component={Link}
          to={{ pathname: "/users/modify", state: { data } }}
          variant="contained"
          color="secondary"
          sx={{ m: 1 }}
        >
          Modify User
        </Button>
        {/* <Button variant="contained" color="error" sx={{ m: 1 }}>
          Remove User
        </Button> */}
        <AlertDialog data={data} />
      </Box>
      <DataGrid
        columns={colDefs}
        rows={rowData}
        pageSize={10}
        rowsPerPageOptions={[10]}
        checkboxSelection
        disableSelectionOnClick
        onSelectionModelChange={(ids) => {
          const selectedIDs = new Set(ids);
          selectedRowData = rowData.filter((row) => selectedIDs.has(row.id));
          const userData = selectedRowData;
          setData(userData);
          setData((state) => {
            return state;
          });
        }}
      />
    </div>
  );
}
