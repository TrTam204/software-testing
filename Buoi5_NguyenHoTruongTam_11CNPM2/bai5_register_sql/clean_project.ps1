# Clean project temporary files: delete 'out' and any temporary sql files
$root = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $root

Write-Host "Cleaning project: removing 'out' directory and temp sql files..."

if (Test-Path out) {
    Remove-Item -Recurse -Force out
    Write-Host "Removed out/"
} else {
    Write-Host "out/ not found"
}

# Remove any temp sqlcmd files if left by previous runs
Get-ChildItem -Path $root -Recurse -Filter "sqlcmd_*.sql" -ErrorAction SilentlyContinue | ForEach-Object {
    try { Remove-Item -Force $_.FullName; Write-Host "Removed $_" } catch {}
}

Write-Host "Clean complete."