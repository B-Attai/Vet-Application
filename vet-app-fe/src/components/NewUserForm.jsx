import React, { useState } from "react";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import OutlinedInput from "@mui/material/OutlinedInput";
import Stack from "@mui/material/Stack";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import { Box, Button } from "@mui/material";
import LocalizationProvider from "@mui/lab/LocalizationProvider";
import DesktopDatePicker from "@mui/lab/DesktopDatePicker";
import TextField from "@mui/material/TextField";
import AdapterDateFns from "@mui/lab/AdapterDateFns";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import { Link } from "react-router-dom"; //added

export default function NewUserForm(props) {
  const [values, setValues] = React.useState({
    username: "",
    password: "",
    email: "",
    role: "",
    activationDate: "",
    status: "",
  });

  const [dateValue, setDateValue] = useState(new Date());
  const [role, setRole] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");

  const usernameInputChangeHandler = (e) => {
    setUsername(e.target.value);
  };

  const passwordInputChangeHandler = (e) => {
    setPassword(e.target.value);
  };

  const emailInputChangeHandler = (e) => {
    setEmail(e.target.value);
  };

  const handleChangeDate = (dateValue) => {
    setDateValue(dateValue);
  };

  const handleRolesChange = (event) => {
    setRole(event.target.value);
  };

  const handleChange = (prop) => (event) => {
    setValues({ ...values, [prop]: event.target.value });
    console.log(values);
  };

  function newUserSubmit(event) {
    event.preventDefault();

    props.onNewUserSubmit({ username, email, password, dateValue, role });
    // console.log(username, email, password, dateValue, type);
  }

  return (
    <Box sx={{ display: "flex", justifyContent: "center" }}>
      <div
        style={{
          position: "absolute",
          left: "50%",
          top: "50%",
          transform: "translate(-50%, -50%)",
        }}
      >
        <Grid
          sx={{ p: 4 }}
          item
          style={{ marginRight: "auto" }} //, border: "1px solid gray" }}
        >
          <Stack spacing={3}>
            <Grid item>
              <Typography variant="h3" color="primary">
                Add New User
              </Typography>
            </Grid>

            <FormControl sx={{ m: 1, width: "40ch" }} variant="outlined">
              <InputLabel htmlFor="outlined-adornment-username">
                Username
              </InputLabel>
              <OutlinedInput
                id="outlined-adornment-username"
                onChange={usernameInputChangeHandler}
                label="Username"
                placeholder="Enter username"
              />
            </FormControl>

            <FormControl sx={{ m: 1, width: "40ch" }} variant="outlined">
              <InputLabel htmlFor="outlined-adornment-email">
                Email Address
              </InputLabel>
              <OutlinedInput
                id="outlined-adornment-email"
                onChange={emailInputChangeHandler}
                label="Email Address"
                placeholder="Enter email address"
              />
            </FormControl>

            <FormControl sx={{ m: 1, width: "40ch" }} variant="outlined">
              <InputLabel htmlFor="outlined-adornment-username">
                Password
              </InputLabel>
              <OutlinedInput
                id="outlined-adornment-username"
                onChange={passwordInputChangeHandler}
                label="Password"
                placeholder="Enter password"
              />
            </FormControl>

            <FormControl fullWidth>
              <InputLabel id="demo-simple-select-label">Role Type</InputLabel>
              <Select
                labelId="role-type-label"
                id="role-type"
                label="Role Type"
                onChange={handleRolesChange}
                placeholder="Select Role"
              >
                <MenuItem value={"admin"}>Admin</MenuItem>
                <MenuItem value={"tt"}>Teaching Technician</MenuItem>
                <MenuItem value={"aca"}>Animal Care Attendant</MenuItem>
                <MenuItem value={"aht"}>Animal Health Technician</MenuItem>
                <MenuItem value={"student"}>Student</MenuItem>
              </Select>
            </FormControl>

            <LocalizationProvider dateAdapter={AdapterDateFns}>
              <DesktopDatePicker
                label="Activation Date"
                inputFormat="MM/dd/yyyy"
                onChange={handleChangeDate}
                renderInput={(params) => <TextField {...params} />}
              />
            </LocalizationProvider>

            {/* <FormControl sx={{ m: 1, width: "40ch" }} variant="outlined">
              <InputLabel htmlFor="outlined-adornment-status">
                Status
              </InputLabel>
              <OutlinedInput
                id="outlined-adornment-status"
                value={values.status}
                onChange={handleChange("status")}
                label="Status"
                placeholder="Enter status"
              />
            </FormControl> */}

            <Button
              onClick={newUserSubmit}
              variant="contained"
              color="success"
              sx={{ m: 1 }}
            >
              Add New User
            </Button>
            <Button
              component={Link}
              to="/users"
              variant="contained"
              color="error"
              sx={{ m: 1 }}
            >
              Cancel
            </Button>
          </Stack>
        </Grid>
      </div>
    </Box>
  );
}
