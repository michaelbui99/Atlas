<#
    Merges main with specified branch (no fast foward) and returns to the specified branch after push. 
    Use . as value for param CurrentBranch to specify the current git branch
#>

param (
    [Parameter(Mandatory = $true)]
    [string] $CurrentBranch
)

if (-not($CurrentBranch)) {
    Throw "Current branch must be specified"
}

if ($CurrentBranch.ToLower -eq "main") {
    Invoke-Expression "git push"
    return
}

if ($CurrentBranch -eq ".") {
    Invoke-Expression "echo Current branch has been set to $CurrentBranch"
    $CurrentBranch = &git rev-parse --abbrev-ref HEAD
}

Invoke-Expression "git checkout main"

Invoke-Expression "git merge $CurrentBranch -no-ff && git push"

Invoke-Expression "git checkout $CurrentBranch"




