function changeMenuName() {
  var mnuName = document.getElementById("mnu");
  var foo = document.getElementById("foot");
  if (mnuName.innerHTML === "Menu") {
    mnuName.innerHTML = "Hide Menu";
    foo.style.width = calc("100vw - 270px");
  } else {
    mnuName.innerHTML = "Menu";
    foo.style.width = "100vw";
  }
}

/*<script data-th-inline="text">*/
  /*<![CDATA[*/
/*  function customSearch() {*/
/*    var valInput = document.getElementById("searchBar");
    var valOK = document.getElementById("okButton");
    valOK.setAttribute("href", "[[@{/infoPage?name=}]]" + valInput.value);
  }*/
  /*]]>*/
/*  </script>*/