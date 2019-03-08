
=========================== Nancy setup & notes ===========================

* Official page: http://nancyfx.org/

* Documentation: https://github.com/NancyFx/Nancy/wiki/Documentation

* Tutorials:
  - https://auth0.com/blog/meet-nancy-a-lightweight-web-framework-for-dot-net/
  - https://volkanpaksoy.com/archive/2015/11/11/building-a-simple-http-server-with-nancy/
  
* Nancy.Hosting.Self - unable to handle concurrent requests (HostConfiguration.MaximumConnectionCount property is missing).
  See: https://github.com/NancyFx/Nancy/issues/2833

1. Install SideWaffle Template Pack (VS extension): https://marketplace.visualstudio.com/items?itemName=MadsKristensen.SideWaffleTemplatePack

2. See Nancy self-hosting instead of hosting on IIS

3. Managing static content - https://github.com/NancyFx/Nancy/wiki/Managing-static-content. See extra steps required when using Owin.