// required by Spring Security for POST request
// var hdr = $("meta[name='_csrf_header']").attr("content");
// var tok = $("meta[name='_csrf']").attr("content");

function fetchAjax(inp, strIn, callback) {
  inp.addEventListener('input', function () {
    var searchName = this.value;
    return $.ajax({
      type: "GET",
      url: (strIn === 'airports') ? "/app/api/airports?search=" + encodeURIComponent(searchName) : "/app/api/airlines?search=" + encodeURIComponent(searchName),
      dataType: "json",
      cache: false,
      data: {}
      // beforeSend: function (xhr) {
      // xhr.setRequestHeader(hdr, tok); // for POST request
      // },
    })
      .done(callback)
      .fail(function(jqXHR, textStatus) {
        console.log("ERROR: ", textStatus)
      });
  });
}

function autocomplete(inp, outp, strIn) {
  var currentFocus;
  fetchAjax(inp, strIn, function (arr) {
    var a, b, i, val = inp.value;
    closeAllLists();
    if (!val) {
      return false;
    }

    currentFocus = -1;
    a = document.createElement("DIV");
    a.setAttribute("id", inp.id + "autocomplete-list");
    a.setAttribute("class", "autocomplete-items");
    outp.parentNode.appendChild(a);

    for (i = 0; i < arr.length; i++) {
      if (arr[i].substr(arr[i].toLowerCase().indexOf(val.toLowerCase()), val.length).toUpperCase() === val.toUpperCase()) {
        b = document.createElement("DIV");
        b.style.borderRadius="5px";
        b.style.border = "1px solid #CEE4FA";
        b.innerHTML = arr[i].substr(0, arr[i].toLowerCase().indexOf(val.toLowerCase()))
          + "<strong>" + arr[i].substr(arr[i].toLowerCase().indexOf(val.toLowerCase()), val.length) + "</strong>";
        b.innerHTML += arr[i].substr(arr[i].toLowerCase().indexOf(val.toLowerCase()) + val.length);
        b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
        b.addEventListener("click", function(e) {
          inp.value = this.getElementsByTagName("input")[0].value;
          closeAllLists();
        });
        a.appendChild(b);
      }
    }
  });

  inp.addEventListener("keydown", function(e) {
    var x = document.getElementById(inp.id + "autocomplete-list");
    if (x) x = x.getElementsByTagName("div");
    if (e.keyCode === 40) {
      currentFocus++;
      addActive(x);
    } else if (e.keyCode === 38) { //up
      currentFocus--;
      addActive(x);
    } else if (e.keyCode === 13) {
      e.preventDefault();
      if (currentFocus > -1) {
        if (x) x[currentFocus].click();
      }
    }
  });

  function addActive(x) {
    if (!x) return false;
    removeActive(x);
    if (currentFocus >= x.length) currentFocus = 0;
    if (currentFocus < 0) currentFocus = (x.length - 1);
    x[currentFocus].classList.add("autocomplete-active");
  }

  function removeActive(x) {
    for (var i = 0; i < x.length; i++) {
      x[i].classList.remove("autocomplete-active");
    }
  }

  function closeAllLists(elmnt) {
    var x = document.getElementsByClassName("autocomplete-items");
    for (var i = 0; i < x.length; i++) {
      if (elmnt !== x[i] && elmnt !== inp) {
        x[i].parentNode.removeChild(x[i]);
      }
    }
  }

  document.addEventListener("click", function (e) {
    closeAllLists(e.target);
  });
}