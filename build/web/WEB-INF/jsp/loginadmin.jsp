<%-- 
    Document   : loginadmin
    Created on : 6 juin 2023, 11:32:40
    Author     : Nomena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Login Admin</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="<% request.getContextPath(); %>assets/img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Roboto:wght@500;700&display=swap" rel="stylesheet"> 
    
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="<% request.getContextPath(); %>assets/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="<% request.getContextPath(); %>assets/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="<% request.getContextPath(); %>assets/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="<% request.getContextPath(); %>assets/css/style.css" rel="stylesheet">
</head>

<body>
    <div class="container-fluid position-relative d-flex p-0">
        <!-- Spinner Start -->
       
        <!-- Spinner End -->


        <!-- Sign In Start -->
        <div class="container-fluid">
            <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
                <div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4">
                    
                    <div class="bg-secondary rounded p-4 p-sm-5 my-4 mx-3">
                        <form action="traitementLoginAdmin" method="get">
                        <div class="d-flex align-items-center justify-content-between mb-3">
                            <a href="index.html" class="">
                                <h3 class="text-primary"><i class="fa fa-user-edit me-2"></i>Nomena</h3>
                            </a>
                            <h3> Admin</h3>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="email" name="email" class="form-control" value="nomena@gmail.com" id="floatingInput" placeholder="nomena@example.com">
                            <label for="floatingInput">Email</label>
                        </div>
                        <div class="form-floating mb-4">
                            <input type="password" name="motdepasse" class="form-control" id="floatingPassword" value="Nomenaihany" placeholder="mot de passe">
                            <label for="floatingPassword">mot de passe</label>
                        </div>
                        <button type="submit" class="btn btn-success py-3 w-100 mb-4">Connexion</button>
                        </form>
                        <a href="retourindex"><button type="submit" class="btn btn-primary py-3 w-100 mb-4">retour</button></a>
                        <p class="text-center mb-0">Nouveau compte ? <a href="inscriptionutilisateur">Inscritpion</a></p>
                    </div>
                    
                </div>
            </div>
        </div>
        <!-- Sign In End -->
    </div>

    <!-- JavaScript Libraries -->
    <script src="http://localhost:8084/evaluation3/assets/cdnjs/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="http://localhost:8084/evaluation3/assets/lib/chart/chart.min.js"></script>
    <script src="http://localhost:8084/evaluation3/assets/lib/easing/easing.min.js"></script>
    <script src="http://localhost:8084/evaluation3/assets/lib/waypoints/waypoints.min.js"></script>
    <script src="http://localhost:8084/evaluation3/assets/lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="http://localhost:8084/evaluation3/assets/lib/tempusdominus/js/moment.min.js"></script>
    <script src="http://localhost:8084/evaluation3/assets/lib/tempusdominus/js/moment-timezone.min.js"></script>
    <script src="http://localhost:8084/evaluation3/assets/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

    <!-- Template Javascript -->
    <script src="http://localhost:8084/evaluation3/assets/js/main.js"></script>
</body>

</html>
