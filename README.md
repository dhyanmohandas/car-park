# CAR PARK

Assignment 

You are required to create a simple service API that will allow a client to manage their parking slot allocation. Parking slots may be queried and managed – allocated, amended or unallocated. 
<ul>
<li>A car park has many parking slots. </li>
<li>A slot could be acquired for a X number of hours, where X >= 1h and X <= 4h  </li>
<li>When the acquired time period elapses, it should be possible to allocate the slot again  </li>
</ul>

Scenarios

<ol>
    <li>User enters a car park and requests a free slot for a number of hours.  </li>
    <li>User chooses to re-allocate their current slot for a number of hours.  </li>
    <li>User leaves car park before the allocated time has passed.  </li>
</ol>

Guidelines 

<ul>
    <li>The solution should be written in Java, Scala  </li>
    <li>Use a TDD approach </li>
    <li>Expose API as REST resources </li>
    <li>Feel free to make any assumptions, ask questions and get us engaged   </li>
    <li>No restrictions on external libraries  </li>
</ul>
Simplifications

<ul>
    <li>Any persistence can be held in memory  </li>
    <li>Ignore authentication and authorization concerns   </li>
    <li>We do not need to handle future reservations.  </li>
    <li>We do not need to maintain a log or history.  </li>
</ul>
