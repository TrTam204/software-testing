# Download Microsoft SQL Server JDBC driver to lib/ (PowerShell)
# Usage: Right-click -> Run with PowerShell, or execute in PowerShell: .\download_mssql_jdbc.ps1

$libDir = Join-Path $PSScriptRoot "lib"
if (-not (Test-Path $libDir)) { New-Item -ItemType Directory -Path $libDir | Out-Null }

# Change version if needed
$version = '12.2.0.jre11'
$jarName = "mssql-jdbc-$version.jar"
$url = "https://repo1.maven.org/maven2/com/microsoft/sqlserver/mssql-jdbc/$version/$jarName"
$dest = Join-Path $libDir $jarName

Write-Host "Downloading $jarName to $dest ..."
try {
    Invoke-WebRequest -Uri $url -OutFile $dest -UseBasicParsing -ErrorAction Stop
    Write-Host "Downloaded successfully." -ForegroundColor Green
    Write-Host "Add $dest to your project's classpath or IDE libraries."
} catch {
    Write-Error "Download failed: $_"
    Write-Host "You can also download manually from: $url"
}
