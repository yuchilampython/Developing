function passwdCheck() {
  var x = document.getElementById("passwdInput");
  var y = document.getElementById("confirmInput");
  if (x.type === "password") {
    x.type = "text";
    y.type = "text";
  } else {
    x.type = "password";
    y.type = "password";
  }
}