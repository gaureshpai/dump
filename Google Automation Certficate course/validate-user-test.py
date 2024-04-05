from validateuser import validate_user

validate_user("", -1)

validate_user(["name"], 1)

validate_user([], 1)

validate_user(88, 1)

validate_user("", 1)
validate_user("myuser", 1)