window.addEventListener("load", initPage, false);

function initPage() {
    // when radio button "JS" is clicked: clicking on button "Filter" is not allowed to send http request
    // and clicking on button "Filter" should activate JS filter function
    document.getElementById("enableJS").addEventListener("click", enableJS, false);

    // when radio button "Java" or "SQL" is clicked: clicking on button "Filter" should send http request
    let requestButtons = document.getElementsByClassName("disableJS")
    for (let i = 0; i < requestButtons.length; i++) {
        requestButtons[i].addEventListener("click", disableJS);
    }

}

/*function preventDefault() {
    function (event) {
        event.preventDefault();
    }
}*/

function getListener(event) {
        event.preventDefault();
}

function enableJS() {
    let button = document.getElementById("button");
    // clicking on button "Filter" is not allowed to send http request
    button.addEventListener("click", getListener)
    // but filters data with JS method
    button.addEventListener("click", filterByJS);

}

function disableJS() {
    let button = document.getElementById("button");
    // clicking on button "Filter" must send http request
    button.removeEventListener("click", getListener);
    // and JS filter function should be disabled
    button.removeEventListener("click", filterByJS);

}

function filterByJS() {
    console.log("geklikt")
    // read data from form fields
    let from = document.getElementById("from").value;
    let until = document.getElementById("until").value;
    // convert data to Date object
    let fromDate = new Date(from);
    let untilDate = new Date(until);
    // hide non-filtered data
    let rows = document.getElementsByTagName("tr");
    for (let i = 1; i < rows.length; i++) {
        // read date as string
        let dateAsString = rows[i].getElementsByClassName("date")[0].innerText;
        // convert string to correct format
        let dateSplitted = dateAsString.split("-");
        // and create Date object
        let date = new Date(dateSplitted[2] + "-" + dateSplitted[1] + "-" + dateSplitted[0]);
        // hide line when necessary
        if (date < fromDate || date > untilDate) {
            rows[i].style = "display: none";
        }
    }
    // reset title
    document.getElementById("h2").innerText="All Appointments " + (!from? "" : ("from " + from + " ")) + (!until ? "" : ("until " + until));


}