
    <!-- top navigation -->
            <div class="top_nav">

                <div class="nav_menu">
                    <nav class="" role="navigation">
                        <div class="nav toggle">
                        
                            <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                            
                        </div>

                        <ul class="nav navbar-nav navbar-right">
                            <li class="">
                                <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                    <img src="images/img.jpg" alt=""><% out.print(session.getAttribute("name")); %>
                                    <span class=" fa fa-angle-down"></span>
                                </a>
                                <ul class="dropdown-menu dropdown-usermenu animated fadeInDown pull-right">
                                
                                    <li><a href="editprofile.jsp">Edit Profile</a>
                                    </li>
                                    
                                    <li><a href="logout.jsp"><i class="fa fa-sign-out pull-right"></i> Log Out</a>
                                    </li>
                                     <li><input type="datetime" style="width:inherit;color:green; " value="<%out.print("Last Login :"+session.getAttribute("lastlogin")); %>"/>
                                    </li>
                                </ul>
                            </li>
                     </ul>
                    </nav>
                </div>

            </div>
    
        
    

