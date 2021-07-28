<!DOCTYPE html>
<head>
    <title>CV</title>
</head>

<body>
<div>
    <h1>${firstName} ${lastName}</h1>
    <p>Age ${age}</p>
    <p>Goal: ${goal}</p>
</div>

</br>

<div>
    <h1>Experiences</h1>
    <#list experiences as exp>
        <p>Company: ${exp.companyName}</p>
        <p>Position: ${exp.position}</p>
        <p>From: ${exp.dateFrom.monthFrom}/${exp.dateFrom.yearFrom}</p>
        <p>To: ${exp.dateTo.monthTo}/${exp.dateTo.yearTo}</p>
        <p>----------------------------------------------------</p>
    </#list>
</div>

</body>
